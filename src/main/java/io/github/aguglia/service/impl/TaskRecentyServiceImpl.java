package io.github.aguglia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.PrioritiesMapper;
import io.github.aguglia.mapper.TaskRecentlyMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.service.TaskRecentyService;

@Service
public class TaskRecentyServiceImpl implements TaskRecentyService {

	@Autowired
	private TaskRecentlyMapper taskRecentlyMapper;
	
	@Autowired
	private PrioritiesMapper prioritiesMapper;
	
	@Override
	public List<TaskModel> taskRecenty(String Userid) {
		
		List<TaskModel> tasks = taskRecentlyMapper.taskRecently(Userid);
		for (TaskModel task : tasks) {
			String prioritie = prioritiesMapper.prioritiesMapper(task.getPriorityID());
			task.setPriorityID(prioritie);
		}
		return tasks;
	}

}
