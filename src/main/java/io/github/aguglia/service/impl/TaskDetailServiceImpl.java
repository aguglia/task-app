package io.github.aguglia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.PriorityMapper;
import io.github.aguglia.mapper.TaskDetailMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;
import io.github.aguglia.service.TaskDetailService;
import io.github.aguglia.utils.DateUtil;

@Service
public class TaskDetailServiceImpl implements TaskDetailService {

	@Autowired
	private TaskDetailMapper taskDetailMapper;

	@Autowired
	private PriorityMapper priorityMapper;

	@Override
	public TaskModel TaskDetail(String taskID) {
		TaskModel Detail = taskDetailMapper.IDtoTask(taskID);
		List<Integer> time = DateUtil.minuteToHour(Detail.getRequiredtime());
		Detail.setRequiredtimehour(time.get(0));
		Detail.setRequiredtimemin(time.get(1));
		return Detail;
	}

	@Override
	public List<TaskSmallModel> TaskSmall(String taskID) {
		List<TaskSmallModel> Small = taskDetailMapper.TaskSmallget(taskID);
		return Small;
	}

	@Override
	public List<String> Priorityget() {

		return priorityMapper.getPriority();
	}

}
