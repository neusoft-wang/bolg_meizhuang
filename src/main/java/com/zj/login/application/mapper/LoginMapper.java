package com.zj.login.application.mapper;

import java.util.Map;

import com.zj.login.domain.Login;

public interface LoginMapper {

	Login getUser(Map<String, Object> map);
	
	int selectRegist(Map<String, Object> map);
	
	int registNewOne(Map<String, Object> map);
	
	int updateHeadIcon(Login login);
}