package com.zj.person.application.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.person.application.mapper.PersonMapper;
import com.zj.person.application.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public Map<String, Object> personDetail(Map<String, Object> map) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> personDetail = personMapper.personDetail(map);
		List<Map<String, Object>> infosList = personMapper.infoList(map);
		List<Map<String, Object>> friendsList = personMapper.friendsList(map);	
		personDetail.put("totalGreat", personMapper.totalGreat(map));
		
		resultMap.put("personDetail", personDetail);
		resultMap.put("infosList", infosList);		
		resultMap.put("friendsList", friendsList);
		return resultMap;
	}

	@Override
	public int friendAdd(Map<String, Object> map) {
		
		return personMapper.friendAdd(map);
	}
	
	@Override
	public int updatePerInfo(Map<String, Object> map) {
		
		return personMapper.updatePerInfo(map);
	}

	@Override
	public int friendDel(Map<String, Object> map) {
		
		return personMapper.friendDel(map);
	}

	@Override
	public List<Map<String, Object>> favouriteDetail(Map<String, Object> map) {
		
		return personMapper.favouriteList(map);
	}

	@Override
	public int favouriteDel(Map<String, Object> map) {
		
		return personMapper.favouriteDel(map);
	}

	@Override
	public int infoDel(Map<String, Object> map) {
		
		return personMapper.infoDel(map);
	}

	@Override
	public Map<String, Object> personInfo(Map<String, Object> map) {
		
		return personMapper.personInfo(map);
	}

}