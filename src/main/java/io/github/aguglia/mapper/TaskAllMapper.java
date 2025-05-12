package io.github.aguglia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.github.aguglia.model.TaskModel;

@Mapper
public interface TaskAllMapper {
	public List<TaskModel> taskAll(String UserID);
}
