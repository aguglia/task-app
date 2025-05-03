package io.github.aguglia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;
import io.github.aguglia.service.TaskDetailService;
import io.github.aguglia.service.TaskSharedService;

@Controller
public class TaskDetailController {
	
	@Autowired
	private TaskDetailService taskDetailService;
	
	@Autowired
	private TaskSharedService taskSharedService;
	
	@GetMapping("/task/items")
	//@ResponseBody
    public String getTaskItem(@RequestParam("ID") String taskId, Model model,Authentication authentication) {
		String username = authentication.getName();
		model.addAttribute("username", username);
		
		//taskIDから色々するServiceを作って処理taskdetailで返す。
		TaskModel taskdetail = taskDetailService.TaskDetail(taskId);
		List<String> ShareName = taskSharedService.ShareUser(taskId);
		taskdetail.setShareID(ShareName.toArray(new String[0]));
		model.addAttribute("taskdetail", taskdetail);
		
		List<TaskSmallModel> tasksmall = taskDetailService.TaskSmall(taskId);
		model.addAttribute("tasksmall", tasksmall);
		
        return "flagments/taskdetail :: taskDetails"; // "fragments/task-item.html" の "taskDetails" フラグメントを返す
    }
}
