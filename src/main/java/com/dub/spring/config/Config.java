package com.dub.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Config {
	
	@Value("${datasource.url}")
	String url;
	
	@Value("${datasource.username}")
	String username;
	
	@Value("${datasource.password}")
	String password;
	
	@Bean
   	public DataSource dataSource() {
   		BasicDataSource dataSource = 
   				new BasicDataSource();		
   		//dataSource.setUrl("jdbc:mysql://localhost:3306/movieDB");
   		//dataSource.setUsername("spring");
   		//dataSource.setPassword("password1234");
   		dataSource.setUrl(url);
   		dataSource.setUsername(username);
   		dataSource.setPassword(password);
   		return dataSource;
   	}// dataSource
   
}
