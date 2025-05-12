package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.TaskNewMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskNewService;

@Service
public class TaskNewServiceImpl implements TaskNewService {

	@Autowired
	private TaskNewMapper tasknewMapper;

	@Override
	public String TaskNew(TaskModel taskmodel) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			tasknewMapper.TaskNew(taskmodel);
			return "登録完了"; // 登録成功後、ログイン画面へリダイレクト
		} catch (Exception e) {
			return "登録処理中にエラーが発生しました。";
		}
	}

}
