package io.github.aguglia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {
	
	@GetMapping("/information")
	public String info() {
		return "information";
	}
}
