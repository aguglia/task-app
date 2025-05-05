package io.github.aguglia.mapper;

import org.apache.ibatis.annotations.Mapper;

import io.github.aguglia.model.TaskModel;

@Mapper
public interface TaskUpdateMapper {
	public void TaskUpdate(TaskModel taskmodel);
}
