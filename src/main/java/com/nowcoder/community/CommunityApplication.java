package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

//扫描配置类所在的包以及自包下的bean
@SpringBootApplication
public class CommunityApplication {

	@PostConstruct
	public void init(){
		// 解决netty启动冲突问题
		// ElasticSearch6.0之后已经被修复了
	}

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
