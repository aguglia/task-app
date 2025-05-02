package io.github.aguglia.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.aguglia.model.TaskModel;

@Controller
public class TaskDetailController {
	
	@GetMapping("/task/items")
	//@ResponseBody
    public String getTaskItem(@RequestParam("ID") String taskId, Model model,Authentication authentication) {
		String username = authentication.getName();
		model.addAttribute("username", username);
		
		//taskIDから色々するServiceを作って処理taskdetailで返す。
		TaskModel taskmodel = new TaskModel();
		taskmodel.setTaskname("test");
		taskmodel.setComent("testタスク");
		model.addAttribute("taskdetail", taskmodel);
		
        return "flagments/taskdetail :: taskDetails"; // "fragments/task-item.html" の "taskDetails" フラグメントを返す
    }
}
