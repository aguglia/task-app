package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.UserSettingMapper;
import io.github.aguglia.model.LoginModel;
import io.github.aguglia.service.UserSettingService;

@Service
public class UserSettingServiceImpl implements UserSettingService {

	@Autowired
	private UserSettingMapper userSettingMapper;
	
	@Override
	public LoginModel userget(String userID) {
		
		LoginModel userdata = userSettingMapper.GetUser(userID);
		
		return userdata;
	}

	@Override
	public String userset(LoginModel usermodel) {
		
		try {
			userSettingMapper.SetUser(usermodel);
			return "登録完了"; // 登録成功後、ログイン画面へリダイレクト
		} catch (Exception e) {
			return "登録処理中にエラーが発生しました。";
		}
	}

}
