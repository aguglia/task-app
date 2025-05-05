package io.github.aguglia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.github.aguglia.model.LoginModel;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;
import io.github.aguglia.model.WeatherResponse;
import io.github.aguglia.service.TaskNewService;
import io.github.aguglia.service.TaskRecentyService;
import io.github.aguglia.service.WeatherResponseService;

@Controller
public class TaskmainController {

	@Autowired
	private TaskNewService tasknewService;

	@Autowired
	private TaskRecentyService taskRecentyService;

	@Autowired
	private WeatherResponseService weatherResponseService;

	@GetMapping("/task")
	public String task(Model model, Authentication authentication) {
		String username = authentication.getName();
		model.addAttribute("username", username);
		TaskModel taskmodel = new TaskModel();
		model.addAttribute("taskmodel", taskmodel);
		model.addAttribute("taskdetail", taskmodel);
		TaskSmallModel tasksmall = new TaskSmallModel();
		model.addAttribute("tasksmall", tasksmall);
		Object userbuf = authentication.getPrincipal();
		List<TaskModel> tasksmodel = null;
		if (userbuf instanceof LoginModel user) {
			LoginModel userData = user;
			tasksmodel = taskRecentyService.taskRecenty(userData.getUserID());
		}

		model.addAttribute("tasksmodel", tasksmodel);

		WeatherResponse weatherInfo = weatherResponseService.getWeatherInfo("nagoya");
		model.addAttribute("description", weatherInfo.getWeather().get(0).getDescription());
		model.addAttribute("temp", weatherInfo.getMain().getTemp());
		return "taskmain";
	}

	@PostMapping("/task")
	public String taskadd(@ModelAttribute TaskModel taskmodel,
			Authentication authentication,
			Model model) {
		System.out.println("登録テスト1");
		model.addAttribute("taskmodel", new TaskModel());
		TaskSmallModel tasksmall = new TaskSmallModel();
		model.addAttribute("tasksmall", tasksmall);
		Object userbuf = authentication.getPrincipal();
		List<TaskModel> tasksmodel = null;
		if (userbuf instanceof LoginModel user) {
			LoginModel userData = user;
			taskmodel.setUserID(userData.getUserID());
			tasksmodel = taskRecentyService.taskRecenty(userData.getUserID());
		} else {
			model.addAttribute("errormessage", "user情報がうまく取得できません。");
			return "taskmain";
		}
		String errorMessage = tasknewService.TaskNew(taskmodel);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("tasksmodel", tasksmodel);
		
		WeatherResponse weatherInfo = weatherResponseService.getWeatherInfo("nagoya");
		model.addAttribute("description", weatherInfo.getWeather().get(0).getDescription());
		model.addAttribute("temp", weatherInfo.getMain().getTemp());
		
		return "taskmain";
	}
}
