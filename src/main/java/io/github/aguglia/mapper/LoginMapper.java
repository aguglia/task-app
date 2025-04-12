package io.github.aguglia.mapper;

import org.apache.ibatis.annotations.Mapper;

import io.github.aguglia.model.LoginModel;

@Mapper
public interface LoginMapper {
	public LoginModel getLoginUser(String id);
}

