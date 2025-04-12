package io.github.aguglia.mapper;

import org.apache.ibatis.annotations.Mapper;

import io.github.aguglia.model.LoginModel;

@Mapper
public interface RegisterMapper {
	public void RegisterUser(LoginModel loginmodel);
	public LoginModel getRegisterUser(String mail);
	
}
