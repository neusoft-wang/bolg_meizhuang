package com.zj.xhs.application.service;

import java.util.List;
import java.util.Map;

import com.zj.xhs.domain.Info;

public interface XhsService {
	
	int newInfo(Map<String, Object> map);
	
	List<Info> selectInfo(Map<String, Object> map);
	
	int selectCountInfo(Map<String, Object> map);
	
	int insertImage(List<Map<String,Object>> list);
	
	Map<String, Object> infoDetail(String param);
	
	List<Map<String,Object>> infoComment(Map<String, Object> map);
	
	int buildComment(Map<String, Object> map);
	
	Map<String, Object> bool(Map<String, Object> map);
	
	int greatAndFavourite(Map<String, Object> map);
	
	String selectLike();
}
