package com.zj.person.application.service;

import java.util.List;
import java.util.Map;


public interface PersonService {
	
	Map<String, Object> personDetail(Map<String, Object> map);
	
	List<Map<String, Object>> favouriteDetail(Map<String, Object> map);
	
	int friendAdd(Map<String, Object> map);
	
	int updatePerInfo(Map<String, Object> map);
	
	int friendDel(Map<String, Object> map);

	int favouriteDel(Map<String, Object> map);
	
	int infoDel(Map<String, Object> map);
	
	Map<String, Object> personInfo(Map<String, Object> map);
}
