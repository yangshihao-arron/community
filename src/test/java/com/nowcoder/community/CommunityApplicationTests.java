package com.nowcoder.community;

import com.nowcoder.community.config.AlphaConfig;
import com.nowcoder.community.dao.AlphaDAO;
import com.nowcoder.community.service.AlphaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.Beans;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //和正式环境用一样的配置类
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Test
    public void testApplicationContext(){
        System.out.println(context);

        AlphaDAO alphaDAO = context.getBean(AlphaDAO.class);
        System.out.println(alphaDAO.select());

          alphaDAO = context.getBean("alphaYsh",AlphaDAO.class);
          System.out.println(alphaDAO.select());
    }

    @Test
    public void testBeanManagement(){
        AlphaService alphaService = context.getBean(AlphaService.class);
        System.out.println(alphaService);
        alphaService = context.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = context.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired //自动注入
    @Qualifier("alphaYsh") //把这个Bean注入进来
    private AlphaDAO alphaDAO;

    @Test
    public void testDI(){
        System.out.println(alphaDAO);
    }

}
