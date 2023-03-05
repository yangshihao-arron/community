package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype") //每次访问都一个新的bean
public class AlphaService {

    @Autowired
    private AlphaDAO alphaDAO;

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

}
