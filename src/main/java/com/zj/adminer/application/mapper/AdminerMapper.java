package com.zj.adminer.application.mapper;

import java.util.List;
import java.util.Map;

public interface AdminerMapper {

	List<Map<String, Object>> list(Map<String, Object> map);
	
	List<Map<String, Object>> tabList(Map<String, Object> map);
	
	List<Map<String, Object>> top0List(Map<String, Object> map);
	
	List<Map<String, Object>> buquanTop(Map<String, Object> map);
	
	List<Map<String, Object>> top1List();
	
	Map<String, Object> infoDetail(String id);

	int infoIsCheck(Map<String, Object> map);
	
	int outTop6(Map<String, Object> map);
	
	int outTab(Map<String, Object> map);
	
	int addTop6(Map<String, Object> map);
	
	int addTab(Map<String, Object> map);
	
	int updateTab(Map<String, Object> map);
	
	int updateTabDD(Map<String, Object> map);
	
	Map<String, Object> selectTop (Map<String, Object> map);
	
	Map<String, Object> checkTab(Map<String, Object> map);

}