package com.zj.login.application.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.login.application.mapper.LoginMapper;
import com.zj.login.application.service.LoginService;
import com.zj.login.domain.Login;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public Login selectUser(Map<String, Object> map) {
		
		return loginMapper.getUser(map);
	}

	@Override
	public int registNewOne(Map<String, Object> map) {
		
		int flag = 0;
		if (loginMapper.selectRegist(map)==0) {
			flag = loginMapper.registNewOne(map);
		}
		return flag;
	}

	@Override
	public int updateHeadIcon(Login login) {
		
		return loginMapper.updateHeadIcon(login);
	}
}