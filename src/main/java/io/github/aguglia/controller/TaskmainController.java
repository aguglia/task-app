package io.github.aguglia.controller;

import java.util.Arrays;
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
import io.github.aguglia.service.TaskAllService;
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
	private TaskAllService taskAllService;

	@Autowired
	private WeatherResponseService weatherResponseService;

	//タスク画面表示
	@GetMapping("/task")
	public String task(Model model, Authentication authentication) {
		//User名表示
		String username = authentication.getName();
		model.addAttribute("username", username);
		TaskModel taskmodel = new TaskModel();
		model.addAttribute("taskmodel", taskmodel);
		model.addAttribute("taskdetail", taskmodel);
		TaskSmallModel tasksmall = new TaskSmallModel();
		model.addAttribute("tasksmall", tasksmall);
		Object userbuf = authentication.getPrincipal();

		//直近とすべてのタスクの表示
		List<TaskModel> tasksmodel = null;
		List<TaskModel> alltaskmodel = null;
		LoginModel userData = null;
		if (userbuf instanceof LoginModel user) {
			userData = user;
			tasksmodel = taskRecentyService.taskRecenty(userData.getUserID());
			alltaskmodel = taskAllService.taskAll(userData.getUserID());
		}

		model.addAttribute("tasksmodel", tasksmodel);

		model.addAttribute("alltasksmodel", alltaskmodel);

		if (userData.getAdress() != null) {
			try {
				WeatherResponse weatherInfo = weatherResponseService.getWeatherInfo(userData.getAdress());
				model.addAttribute("description", weatherInfo.getWeather().get(0).getDescription());
				model.addAttribute("temp", weatherInfo.getMain().getTemp());
			} catch (Exception e) {
				model.addAttribute("description", "住所設定ミス");
				model.addAttribute("temp", "住所設定ミス");
			}
		} else {
			model.addAttribute("description", "住所未設定");
			model.addAttribute("temp", "住所未設定");
		}

		//優先度
		List<String> priority = Arrays.asList("高", "中", "低");
		model.addAttribute("priorityList", priority);
		return "taskmain";
	}

	//タスク登録
	@PostMapping("/task")
	public String taskadd(@ModelAttribute TaskModel taskmodel,
			Authentication authentication,
			Model model) {
		TaskModel sendtaskmodel = new TaskModel();
		model.addAttribute("taskmodel", sendtaskmodel);
		model.addAttribute("taskdetail", sendtaskmodel);
		TaskSmallModel tasksmall = new TaskSmallModel();
		model.addAttribute("tasksmall", tasksmall);
		Object userbuf = authentication.getPrincipal();

		//User情報の取得
		LoginModel userData;
		if (userbuf instanceof LoginModel user) {
			userData = user;
			taskmodel.setUserID(userData.getUserID());

		} else {
			model.addAttribute("errormessage", "user情報がうまく取得できません。");
			return "taskmain";
		}
		String errorMessage = tasknewService.TaskNew(taskmodel);
		model.addAttribute("errorMessage", errorMessage);

		List<TaskModel> tasksmodel = taskRecentyService.taskRecenty(userData.getUserID());
		model.addAttribute("tasksmodel", tasksmodel);

		List<TaskModel> alltaskmodel = taskAllService.taskAll(userData.getUserID());
		model.addAttribute("alltasksmodel", alltaskmodel);

		//天気表示
		if (userData.getAdress() != null) {
			try {
				WeatherResponse weatherInfo = weatherResponseService.getWeatherInfo(userData.getAdress());
				model.addAttribute("description", weatherInfo.getWeather().get(0).getDescription());
				model.addAttribute("temp", weatherInfo.getMain().getTemp());
			} catch (Exception e) {
				model.addAttribute("description", "住所設定ミス");
				model.addAttribute("temp", "住所設定ミス");
			}
		} else {
			model.addAttribute("description", "住所未設定");
			model.addAttribute("temp", "住所未設定");
		}

		//優先度
		List<String> priority = Arrays.asList("高", "中", "低");
		model.addAttribute("priorityList", priority);

		return "taskmain";
	}
}
