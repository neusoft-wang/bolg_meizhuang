package com.zj.xhs.application.mapper;

import java.util.List;
import java.util.Map;

import com.zj.xhs.domain.Info;

public interface XhsMapper {

	int newInfo(Map<String, Object> map);
	
	List<Info> selectInfo(Map<String, Object> map);
	
	int selectCountInfo(Map<String, Object> map);
	
	int insertImage(List<Map<String,Object>> list);
	
	void updateFImages(Map<String,Object> map);
	
	Map<String,Object> infoDetail(String param);
	
	List<Map<String,Object>> infoComment(Map<String,Object> param);
	
	int buildComment(Map<String, Object> map);
	
	int boolGreat(Map<String, Object> map);
	
	int boolFavourite(Map<String, Object> map);
	
	int insertGreat(Map<String, Object> map);
	
	int insertFavourite(Map<String, Object> map);
}