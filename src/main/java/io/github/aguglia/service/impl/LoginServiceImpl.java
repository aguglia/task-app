package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.LoginMapper;
import io.github.aguglia.model.LoginModel;
import io.github.aguglia.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService , UserDetailsService{
	
	@Autowired
	LoginMapper mapper;

	@Override
	public LoginModel login(String id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.getLoginUser(username);
	}

}
