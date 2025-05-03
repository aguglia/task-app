package io.github.aguglia.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SharedUserMapper {

	public List<String> SharedUserID(String taskID);
	public String SharedUserName(String UserID);
}
