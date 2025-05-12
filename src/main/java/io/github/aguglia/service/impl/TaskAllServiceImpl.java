package io.github.aguglia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.TaskAllMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskAllService;
import io.github.aguglia.utils.DateUtil;

@Service
public class TaskAllServiceImpl implements TaskAllService {

	@Autowired
	private TaskAllMapper taskAllMapper;

	@Override
	public List<TaskModel> taskAll(String Userid) {
		// TODO 自動生成されたメソッド・スタブ
		List<TaskModel> alltask = taskAllMapper.taskAll(Userid);
		for (TaskModel task : alltask) {
			List<Integer> time = DateUtil.minuteToHour(task.getRequiredtime());
			task.setRequiredtimehour(time.get(0));
			task.setRequiredtimemin(time.get(1));
		}
		return alltask;
	}

}
