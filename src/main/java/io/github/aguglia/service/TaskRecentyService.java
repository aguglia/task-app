package io.github.aguglia.service;

import java.util.List;

import io.github.aguglia.model.TaskModel;

public interface TaskRecentyService {
	public List<TaskModel> taskRecenty(String Userid);
}
