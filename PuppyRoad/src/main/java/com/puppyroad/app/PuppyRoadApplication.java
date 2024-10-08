package com.puppyroad.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.puppyroad.app.**.mapper")
@MapperScan(basePackages = "com.puppyroad.app.admin.**.mapper")
public class PuppyRoadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuppyRoadApplication.class, args);
	}

}
