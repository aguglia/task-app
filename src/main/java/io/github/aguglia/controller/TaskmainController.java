package io.github.aguglia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.github.aguglia.model.LoginModel;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskNewService;

@Controller
public class TaskmainController {

	@Autowired
	private TaskNewService tasknewService;

	@GetMapping("/task")
	public String task(Model model, Authentication authentication) {
		String username = authentication.getName();
		model.addAttribute("username", username);
		TaskModel taskmodel = new TaskModel();
		model.addAttribute("taskmodel", taskmodel);
		return "taskmain";
	}

	@PostMapping("/task")
	public String taskadd(@ModelAttribute TaskModel taskmodel,
			Authentication authentication,
			Model model) {
		System.out.println("登録テスト1");
		model.addAttribute("taskmodel", new TaskModel());
		Object userbuf = authentication.getPrincipal();
		if (userbuf instanceof LoginModel user) {
			LoginModel userData = user;
			taskmodel.setUserID(userData.getUserID());
		} else {
			model.addAttribute("errormessage", "user情報がうまく取得できません。");
			return "taskmain";
		}
		String errorMessage = tasknewService.TaskNew(taskmodel);
		model.addAttribute("errorMessage", errorMessage);
		return "taskmain";
	}
}
