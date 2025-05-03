package io.github.aguglia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.github.aguglia.model.TaskModel;
import io.github.aguglia.model.TaskSmallModel;

@Mapper
public interface TaskDetailMapper {
	public TaskModel IDtoTask(String taskID);
	public List<TaskSmallModel> TaskSmallget(String taskID);
}
