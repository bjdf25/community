package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IDiscussPostDao {

    List<DiscussPost> selectDiscussPosts(@Param("userId")int userId,@Param("offset")int offset, @Param("limit")int limit);

    //@Param用于给参数取别名，如果方法参数只有一个，并且该参数在动态sql里使用，就必须加别名，否则会报错
    int selectDiscussPostRows(@Param("userId")int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(@Param("id") int id,@Param("commentCount") int commentCount);

    int updateType(@Param("id")int id , @Param("type")int type);

    int updateStatus(@Param("id")int id,@Param("status") int status);
}
