package io.github.aguglia.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrioritiesMapper {
	public String prioritiesMapper(String prioritie);
}
