package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.common.Url;

@Controller
public class LoginController {

	@GetMapping(value= {Url.AUTH.LOGIN})
	public String login() {
		
		return Url.AUTH.LOGIN_JSP;
	}
	
	@GetMapping(Url.AUTH.JOIN)
	public String join() {
		return Url.AUTH.JOIN_JSP;
	}
	
}