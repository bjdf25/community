package com.nowcoder.community.service;

import com.nowcoder.community.dao.ICommentDao;
import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.utils.CommunityConstant;
import com.nowcoder.community.utils.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentService implements CommunityConstant{

    @Autowired
    private ICommentDao commentDao;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;
    public List<Comment> findCommentByEntity(int entityType, int entityId, int offset, int limit){
        return commentDao.selectCommentByEntity(entityType,entityId,offset,limit);
    }
    public int findCommentCount(int entityTpye,int entityId){
        return commentDao.selectCountByEntity(entityTpye,entityId);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int addComment(Comment comment){
        if (comment == null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        //添加评论
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));

        int rows = commentDao.insertComment(comment);
        //更新帖子评论数量
        if (comment.getEntityType()== ENTITY_TYPE_POST){
            int count = commentDao.selectCountByEntity(ENTITY_TYPE_POST, comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(),count);
        }

        return rows;
    }

    public Comment findCommentById(int id){
        return commentDao.selectCommentById(id);
    }

}
