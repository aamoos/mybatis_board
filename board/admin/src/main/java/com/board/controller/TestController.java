package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.dao.TestMapper;
import com.board.service.TestService;

@RestController
public class TestController {


	@Autowired 
	TestService testService;
	
	@Autowired 
	TestMapper testMapper;
	
	@GetMapping("/test")
	public String test() {
		testService.test();
		System.out.println(testMapper.test());
		return "ok";
	}
	
}