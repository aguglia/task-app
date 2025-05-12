package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.TaskUpdateMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskUpdateService;
import io.github.aguglia.utils.DateUtil;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

	@Autowired
	private TaskUpdateMapper taskUpdateMapper;

	@Override
	public String updateTask(TaskModel task) {
		Integer min = DateUtil.hourToMinite(task.getRequiredtimehour(), task.getRequiredtimemin());
		task.setRequiredtime(min);
		taskUpdateMapper.TaskUpdate(task);
		try {
			return "登録完了"; // 登録成功後、ログイン画面へリダイレクト
		} catch (Exception e) {
			return "登録処理中にエラーが発生しました。";
		}
		
	}

	

}
