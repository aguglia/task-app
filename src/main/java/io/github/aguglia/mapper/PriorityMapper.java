package io.github.aguglia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PriorityMapper {
	public List<String> getPriority();
}
