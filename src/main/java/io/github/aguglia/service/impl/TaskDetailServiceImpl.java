package io.github.aguglia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.PrioritiesMapper;
import io.github.aguglia.mapper.TaskDetailMapper;
import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;
import io.github.aguglia.service.TaskDetailService;

@Service
public class TaskDetailServiceImpl implements TaskDetailService {

	@Autowired
    private TaskDetailMapper taskDetailMapper;
	
	@Autowired
	private PrioritiesMapper prioritiesMapper;
	
	@Override
	public TaskModel TaskDetail(String taskID) {
		TaskModel Detail= taskDetailMapper.IDtoTask(taskID);
		String prioritie = prioritiesMapper.prioritiesMapper(Detail.getPriorityID());
		Detail.setPriorityID(prioritie);
		return Detail;
	}


	@Override
	public List<TaskSmallModel> TaskSmall(String taskID) {
		List<TaskSmallModel> Small = taskDetailMapper.TaskSmallget(taskID);
		return Small;
	}
	
	

}
