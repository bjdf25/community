package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IMessageDao {

    List<Message> selectConversations(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    int selectConversationCount(int userId);

    List<Message> selectLetters(@Param("conversationId") String conversationId, @Param("offset") int offset, @Param("limit") int limit);

    int selectLetterCount(String conversationId);

    int selectLetterUnreadCount(@Param("userId") int userId,@Param("conversationId") String conversationId);

    int insertMessage(Message message);

    int updateStatus(@Param("ids") List<Integer> ids,@Param("status") int status);

    Message selectLatestNotice(@Param("userId") int userId,@Param("topic") String topic);

    int selectNoticeCount(@Param("userId")int userId,@Param("topic") String topic);

    int selectNoticeUnreadCount(@Param("userId")int userId, @Param("topic")String topic);

    List<Message> selectNotices(@Param("userId") int userId, @Param("topic") String topic,@Param("offset") int offset ,@Param("limit") int limit);
}
