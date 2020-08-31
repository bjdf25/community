package com.nowcoder.community.service;

import com.nowcoder.community.dao.IMessageDao;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.utils.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private IMessageDao messageDao;

    @Autowired
    private SensitiveFilter sensitiveFilter;
    public List<Message> findConversations(int userId, int offset, int limit){
        return messageDao.selectConversations(userId,offset,limit);
    }

    public int findConversationCount(int userId){
        return messageDao.selectConversationCount(userId);
    }

    public List<Message> findLetters(String conversationId, int offset, int limit){
        return messageDao.selectLetters(conversationId,offset,limit);
    }

    public int findLetterCount(String conversationId){
        return messageDao.selectLetterCount(conversationId);
    }

    public int findLetterUnreadCount(int userId, String conversationId){
        return messageDao.selectLetterUnreadCount(userId,conversationId);
    }

    public int addMessage(Message message){
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageDao.insertMessage(message);
    }

    public int readMessage(List<Integer> ids){
        return messageDao.updateStatus(ids,1);
    }

    public Message findLatestNotice(int userId, String topic){
        return messageDao.selectLatestNotice(userId, topic);
    }

    public int findNoticeCount(int userId, String topic){
        return messageDao.selectNoticeCount(userId, topic);
    }

    public int findNoticeUnreadCount(int userId, String topic){
        return messageDao.selectNoticeUnreadCount(userId, topic);
    }


    public List<Message> findNotices(int userId, String topic ,int offset, int limit){
        return messageDao.selectNotices(userId,topic,offset,limit);
    }
}
