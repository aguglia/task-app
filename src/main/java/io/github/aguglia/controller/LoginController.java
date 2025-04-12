package io.github.aguglia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.aguglia.model.LoginModel;

@Controller
public class LoginController {
	@GetMapping("login")
	public String login() {

		System.out.println("first");
		return "login";
	}
	
	@GetMapping("/")
	public String loginUser(LoginModel login) {
		return "top";

	}
}
