package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary //这个bean会被优先装配
public class AlphaDaoMyBatisImpl implements AlphaDAO{
    @Override
    public String select() {
        return "Mybatis";
    }
}
