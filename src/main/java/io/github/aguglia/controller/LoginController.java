package io.github.aguglia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.aguglia.model.LoginModel;
import io.github.aguglia.model.WeatherResponse;
import io.github.aguglia.service.WeatherResponseService;

@Controller
public class LoginController {
	
	@Autowired
	private WeatherResponseService weatherResponseService;
	
	@GetMapping("login")
	public String login() {

		System.out.println("first");
		
		return "login";
	}
	
	@GetMapping("/")
	public String loginUser(LoginModel login,Model model, Authentication authentication) {
		WeatherResponse weatherInfo = weatherResponseService.getWeatherInfo("nagoya");
		model.addAttribute("description", weatherInfo.getWeather().get(0).getDescription());
		model.addAttribute("temp", weatherInfo.getMain().getTemp());
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "top";

	}
}
