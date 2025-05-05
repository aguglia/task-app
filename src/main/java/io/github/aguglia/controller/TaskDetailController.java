package io.github.aguglia.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.aguglia.model.LoginModel;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;
import io.github.aguglia.service.TaskDetailService;
import io.github.aguglia.service.TaskSharedService;
import io.github.aguglia.service.TaskUpdateService;

@Controller
public class TaskDetailController {

	@Autowired
	private TaskDetailService taskDetailService;

	@Autowired
	private TaskSharedService taskSharedService;

	@Autowired
	private TaskUpdateService taskupdateService;

	@GetMapping("/task/items")
	//@ResponseBody
	public String getTaskItem(@RequestParam("ID") String taskId, Model model, Authentication authentication) {
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

	@GetMapping("/tasks/update")
	public String updateTaskAndGetFragment(
			@RequestParam("ID") String id,
			@RequestParam(value = "taskname", required = false) String taskname,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "deadlinedate", required = false) String deadlinedate,
			@RequestParam(value = "Requiredtime", required = false) String requiredTime,
			@RequestParam(value = "coment", required = false) String coment,
			@RequestParam(value = "priority", required = false) String priority,
			Authentication authentication,
			Model model) {
		TaskModel updatedTask = new TaskModel();
		Object userbuf = authentication.getPrincipal();
		if (userbuf instanceof LoginModel user) {
			LoginModel userData = user;
			updatedTask.setUserID(userData.getUserID());
		}
		updatedTask.setID(id);
		updatedTask.setTaskname(taskname);
		updatedTask.setContent(content);
		// 開始日の処理
		if (startdate != null && !startdate.isEmpty()) {
			try {
				updatedTask.setStartdate(LocalDate.parse(startdate));
			} catch (DateTimeParseException e) {
				System.err.println("開始日の解析エラー: " + startdate);
				// 必要に応じてエラーハンドリング (ユーザーにエラーメッセージを表示するなど)
				updatedTask.setStartdate(null); // 解析失敗時は null をセット
			}
		} else {
			updatedTask.setStartdate(null);
		}

		// 期限日の処理
		if (deadlinedate != null && !deadlinedate.isEmpty()) {
			try {
				updatedTask.setDeadlinedate(LocalDate.parse(deadlinedate));
			} catch (DateTimeParseException e) {
				System.err.println("期限日の解析エラー: " + deadlinedate);
				// 必要に応じてエラーハンドリング
				updatedTask.setDeadlinedate(null); // 解析失敗時は null をセット
			}
		} else {
			updatedTask.setDeadlinedate(null);
		}
		if (requiredTime != null && !requiredTime.isEmpty()) {
	        try {
	            updatedTask.setRequiredtime(Integer.parseInt(requiredTime));
	        } catch (NumberFormatException e) {
	            System.err.println("見積もり時間の解析エラー: " + requiredTime);
	            // 必要に応じてエラーハンドリング (ユーザーにエラーメッセージを表示するなど)
	            updatedTask.setRequiredtime(null); // 解析失敗時は null をセット
	        }
	    } else {
	        updatedTask.setRequiredtime(null);
	    }
		updatedTask.setComent(coment);
		updatedTask.setPriority(priority);

		taskupdateService.updateTask(updatedTask); // データベースを更新
		
		TaskModel taskdetail = taskDetailService.TaskDetail(id);

		model.addAttribute("taskdetail", taskdetail);
		String username = authentication.getName();
		model.addAttribute("username", username);
		// 必要に応じて、共同担当者 (shareID) や小タスク (tasksmall) の情報を取得して model に追加
		
		TaskSmallModel tasksmall = new TaskSmallModel();
		model.addAttribute("tasksmall", tasksmall);

		return "flagments/taskdetail :: taskDetails"; // Thymeleaf のフラグメントを返す
	}
}
