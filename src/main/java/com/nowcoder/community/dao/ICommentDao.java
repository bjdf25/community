package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ICommentDao {

    List<Comment> selectCommentByEntity(@Param("entityType")int entityType,@Param("entityId") int entityId,@Param("offset") int offset,@Param("limit") int limit);

    int selectCountByEntity(@Param("entityType") int entityType,@Param("entityId") int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(int id);
}
