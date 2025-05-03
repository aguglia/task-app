package io.github.aguglia.service;

import java.util.List;

import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;

public interface TaskDetailService {
	public TaskModel TaskDetail(String taskID);
	public List<TaskSmallModel> TaskSmall(String taskID);
}
