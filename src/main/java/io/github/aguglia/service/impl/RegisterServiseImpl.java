package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.RegisterMapper;
import io.github.aguglia.model.LoginModel;
import io.github.aguglia.model.RegisterModel;
import io.github.aguglia.service.RegisterService;

@Service
public class RegisterServiseImpl implements RegisterService{
	
    @Autowired
    private RegisterMapper registerMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public String register(RegisterModel registerModel) {
		//登録しようとしてるユーザーの重複確認
		//登録処理
		
        if (!registerModel.getPassword().equals(registerModel.getConfirmPassword())) {
            return "パスワードと確認用パスワードが一致しません。";
        }

        // ユーザー名が既に存在するか確認 (必要に応じて)
        LoginModel existingUser = registerMapper.getRegisterUser(registerModel.getEmail());
        if (existingUser != null) {
            return "既に登録されています。";
        }
//
        // パスワードをハッシュ化
        String hashedPassword = passwordEncoder.encode(registerModel.getPassword());

        // ユーザー情報を LoginModel に設定
        LoginModel newUser = new LoginModel();
        newUser.setUsername(registerModel.getUsername()); // username を ID として使用 (適宜変更)
        newUser.setPassword(hashedPassword);
        newUser.setEmail(registerModel.getEmail());
//        // 必要に応じて他の情報も設定 (例: ロール)
//        System.out.println(newUser.getUsername());
//        System.out.println(newUser.getPassword());
//        System.out.println(newUser.getEmail());
        // データベースに登録
        try {
        	registerMapper.RegisterUser(newUser);
            return null; // 登録成功後、ログイン画面へリダイレクト
        } catch (Exception e) {
            return "登録処理中にエラーが発生しました。";
        }
	}

}
