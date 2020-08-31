package com.nowcoder.community;

import com.nowcoder.community.dao.IDiscussPostDao;
import com.nowcoder.community.dao.ILoginTicketDao;
import com.nowcoder.community.dao.IMessageDao;
import com.nowcoder.community.dao.IUserDao;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private ILoginTicketDao loginTicketDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IDiscussPostDao discussPostDao;
    @Autowired
    private IMessageDao messageDao;
    @Test
    public void testSelectUser(){
//        User user = userDao.selectById(101);
//        System.out.println(user);
        System.out.println(UUID.randomUUID());
    }
    @Test
    public void testSelectUser1(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("www.5240.com");
        user.setSalt("abc");
        user.setCreateTime(new Date());
        int i = userDao.insertUser(user);
        System.out.println(i);
        System.out.println(user.getId());

    }
    @Test
    public void testSelectUser2(){
//        List<DiscussPost> list = discussPostDao.selectDiscussPosts(149,0,10);
//        for (DiscussPost post : list) {
//            System.out.println(post);
//        }
//        int i = discussPostDao.selectDiscussPostRows(0);
//        System.out.println(i);
        System.out.println(new Date(System.currentTimeMillis() + 3600*1000));
    }
    @Test
    public void testInsetLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicketDao.insertLoginTicket(loginTicket);

    }
    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketDao.selectByTicket("abc");
        System.out.println(loginTicket);
        loginTicketDao.updateStatus("abc",1);
        loginTicket = loginTicketDao.selectByTicket("abc");
        System.out.println(loginTicket);
    }
    @Test
    public void testInsertDiscussPost(){
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(1);
        discussPost.setCommentCount(20);
        discussPost.setContent("嘻嘻");
        discussPost.setCreateTime(new Date());
        discussPost.setStatus(0);
        discussPost.setScore(2);
        discussPost.setTitle("哈哈");
        discussPostDao.insertDiscussPost(discussPost);
    }

    @Test
    public void testSelectMessage(){
        List<Message> list = messageDao.selectConversations(111, 0, 20);
        for (Message message : list) {
            System.out.println(message);
        }
        int i = messageDao.selectConversationCount(111);
        System.out.println(i);
    }
}
