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
		
		return "login";
	}
	
	@GetMapping("/")
	public String loginUser(LoginModel login,Model model, Authentication authentication) {
		
		
		Object userbuf = authentication.getPrincipal();
		LoginModel userData = null;
		if (userbuf instanceof LoginModel user) {
			userData = user;
		}
		if (userData.getAdress() != null) {
			try {
				WeatherResponse weatherInfo = weatherResponseService.getWeatherInfo(userData.getAdress());
				model.addAttribute("description", weatherInfo.getWeather().get(0).getDescription());
				model.addAttribute("temp", weatherInfo.getMain().getTemp());
				}catch(Exception e){
					model.addAttribute("description", "住所設定ミス");
					model.addAttribute("temp", "住所設定ミス");
				}
		} else {
			model.addAttribute("description", "住所未設定");
			model.addAttribute("temp", "住所未設定");
		}
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "top";

	}
}
