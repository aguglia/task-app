package io.github.aguglia.service;

import java.util.List;

import io.github.aguglia.model.TaskModel;

public interface TaskAllService {
	public List<TaskModel> taskAll(String Userid);
}
