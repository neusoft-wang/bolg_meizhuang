package com.zj.adminer.application.service;

import java.util.List;
import java.util.Map;


public interface AdminerService {
	
	List<Map<String, Object>> list(Map<String, Object> map);

	Map<String, Object> infoDetail(String id);

	int infoIsCheck(Map<String, Object> map);
	
	int addTop6(Map<String, Object> map);

	List<Map<String, Object>> topList(Map<String, Object> map);

}
