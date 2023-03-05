package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaYsh")  //bean自定义名字
public class AlphaDaoImpl implements AlphaDAO{
    @Override
    public String select() {
        return "hello ysh";
    }
}
