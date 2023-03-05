package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //和正式环境用一样的配置类
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEamil("153@qq.com");
        user.setHeaderUrl("www.baidu.com");
        user.setCreateTime(new Date());
        int count = userMapper.insertUser(user);
        System.out.println(count);
    }

    @Test
    public void updateUser(){
        int count = userMapper.updateStatus(150, 1);
        System.out.println(count);
        count = userMapper.updateHeader(101,"www.baidu.com");
        System.out.println(count);

         count = userMapper.updatePassword(101,"abc");
         System.out.println(count);
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        for(DiscussPost post : list)
            System.out.println(post);

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

}
