package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //考虑将来功能适用的程度，用户主页查看帖子时需要
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //userId == 0 不拼接这个条件
    //动态拼接，只有一个参数时，并且在<if>里使用，需要加别名
    int selectDiscussPostRows(@Param("userId")int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

}
