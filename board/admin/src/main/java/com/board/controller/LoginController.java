package com.board.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.board.common.Url;
import com.board.entity.UserInfo;
import com.board.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	//로그인 화면
	@GetMapping(value= {Url.AUTH.LOGIN})
	public String login() {
		
		return Url.AUTH.LOGIN_JSP;
	}
	
	//회원가입 화면
	@GetMapping(Url.AUTH.JOIN)
	public String join() {
		return Url.AUTH.JOIN_JSP;
	}
	
	//메인화면
	@GetMapping(Url.MAIN.MAIN)
	public String main() {
		return Url.MAIN.MAIN_JSP;
	}
	
	//사용자 등록
	@PostMapping(Url.AUTH.INSERT_USER)
	@ResponseBody
	public Map<String, Object> insertUser(@ModelAttribute UserInfo userInfo) {
		
		//회원 등록
		return loginService.checkLoginInsert(userInfo);
	}
	
}