package io.github.aguglia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.aguglia.model.RegisterModel;
import io.github.aguglia.service.RegisterService;

@Controller
public class RegisterController {
	
    
    
    @Autowired
    private RegisterService registerService;
	
	@GetMapping("/register")
    public String registerForm(Model model) {
		RegisterModel registermodel = new RegisterModel();
		model.addAttribute("registermodel",registermodel);
        return "register"; // register.html を表示
    }
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegisterModel registermodel,
            					RedirectAttributes redirectAttributes,
            					Model model) {
		
		String errorMessage = registerService.register(registermodel);
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			return "register";
		}
    	redirectAttributes.addFlashAttribute("registerSuccessMessage", "Registration successful! Please log in.");
    	return "redirect:/login"; // 登録成功後、ログイン画面へリダイレクト

	}
}
