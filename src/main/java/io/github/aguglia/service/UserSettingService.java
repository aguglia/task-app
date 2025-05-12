package io.github.aguglia.service;

import io.github.aguglia.model.LoginModel;

public interface UserSettingService {
	public LoginModel userget(String userID);
	public String userset(LoginModel usermodel);
}
