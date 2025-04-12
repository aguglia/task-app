package io.github.aguglia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
	
	@GetMapping("/register")
    public String registerForm() {
        return "register"; // register.html を表示
    }
	
}
