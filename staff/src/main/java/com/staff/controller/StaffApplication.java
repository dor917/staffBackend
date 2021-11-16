package com.staff.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.Socket;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.staff.util.ListeningThread;
import com.staff.util.WritingThread;


@SpringBootApplication
public class StaffApplication {
	public static void main(String[] args) {
		SpringApplication.run(StaffApplication.class, args);
		
	}


}
