package io.github.aguglia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.TaskRecentlyMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskRecentyService;

@Service
public class TaskRecentyServiceImpl implements TaskRecentyService {

	@Autowired
	private TaskRecentlyMapper taskRecentlyMapper;
	
	
	@Override
	public List<TaskModel> taskRecenty(String Userid) {
		
		List<TaskModel> tasks = taskRecentlyMapper.taskRecently(Userid);
		return tasks;
	}

}
