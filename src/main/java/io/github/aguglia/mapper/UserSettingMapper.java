package io.github.aguglia.mapper;

import org.apache.ibatis.annotations.Mapper;

import io.github.aguglia.model.LoginModel;

@Mapper
public interface UserSettingMapper {
	public LoginModel GetUser(String userID);
	public void SetUser(LoginModel user);
}
