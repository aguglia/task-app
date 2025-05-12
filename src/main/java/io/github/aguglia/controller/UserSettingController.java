package io.github.aguglia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.github.aguglia.model.LoginModel;
import io.github.aguglia.service.UserSettingService;

@Controller
public class UserSettingController {
	
	@Autowired
	private UserSettingService userSettingService;
	
	@GetMapping("/usersetting")
	public String Setting(Model model, Authentication authentication) {
		
		Object userbuf = authentication.getPrincipal();
		LoginModel userData = null;
		if (userbuf instanceof LoginModel user) {
			userData = user;
		}
		LoginModel usermodel = userSettingService.userget(userData.getUserID());
		model.addAttribute("usermodel", usermodel);
		return "/usersetting";
	}
	
	@PostMapping("/usersetting")
	public String DBSetting(@ModelAttribute LoginModel usermodel,
			Model model) {
		String Message = userSettingService.userset(usermodel);
		
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		Authentication newAuth = new UsernamePasswordAuthenticationToken(
				usermodel,
                currentAuth.getCredentials(), // 現在の認証の資格情報 (例: パスワード) を保持
                currentAuth.getAuthorities() // 現在の認証の権限を保持
        );
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		model.addAttribute("errormessage", Message);
		model.addAttribute("usermodel", usermodel);
		return "/usersetting";
		
	}
}
