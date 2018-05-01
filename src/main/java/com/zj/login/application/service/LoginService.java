package com.zj.login.application.service;

import java.util.Map;

import com.zj.login.domain.Login;

public interface LoginService {
	
	Login selectUser(Map<String, Object> map);
	
	int registNewOne(Map<String, Object> map);
	
	int updateHeadIcon(Login login);
}
