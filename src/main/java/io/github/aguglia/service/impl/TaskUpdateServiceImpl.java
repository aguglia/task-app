package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.TaskUpdateMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskUpdateService;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

	@Autowired
	private TaskUpdateMapper taskUpdateMapper;

	@Override
	public void updateTask(TaskModel task) {
		taskUpdateMapper.TaskUpdate(task);
		
	}

	

}
