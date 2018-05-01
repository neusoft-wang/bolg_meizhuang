package com.zj.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zj.person.application.mapper.PersonMapper;
import com.zj.person.domain.LikeType;

@Component
public class MyStartupRunner implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	
	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
