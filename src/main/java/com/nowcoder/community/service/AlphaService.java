package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDAO;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
//@Scope("prototype") //每次访问都一个新的bean
public class AlphaService {

    @Autowired
    private AlphaDAO alphaDAO;

    @Autowired

    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public String find(){
        return alphaDAO.select();
    }


    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct  //在构造方法之后调用
    public void init(){
        System.out.println("初始化 AlphaService");
    }

    @PreDestroy  //在销毁对象之前调用
    public void destroy(){
        System.out.println("销毁AlphaService");
    }


    //REQUIRED: 支持当前事务(外部事务)，如果不存在则创建新事物
    //REQUIRES_NEW: 创建一个新的事务，并且暂停当前事务
    //NESTED:如果当前存在事务(外部事务),则嵌套在该事务中执行(独立的提交和回滚)，否则就会和REQUIRED一样
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation =  Propagation.REQUIRED)
    public Object save1(){
        //新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
        user.setEmail("alpha@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        //新增帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle("hello");
        post.setContent("新人报道");
        post.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(post);

        //制造错误
        Integer.valueOf("abc");

        return "ok";
    }


    public Object save2(){
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                //新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
                user.setEmail("beta@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                //新增帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(user.getId());
                post.setTitle("你好");
                post.setContent("我是新人");
                post.setCreateTime(new Date());
                //制造错误
                Integer.valueOf("abc");
                return "ok";
            }
        });
    }

}
