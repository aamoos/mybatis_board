package com.board.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.board.common.Constants;

@SpringBootApplication
@ComponentScan(basePackages = Constants.APP_DEFAULT_PACKAGE_NAME)
@MapperScan(basePackages = Constants.MAPPER_PACKAGE)
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}