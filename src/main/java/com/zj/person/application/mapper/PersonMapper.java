package com.zj.person.application.mapper;

import java.util.List;
import java.util.Map;

import com.zj.person.domain.LikeType;

public interface PersonMapper {

	List<Map<String, Object>> infoList(Map<String, Object> map);
	
	Map<String, Object> personDetail(Map<String, Object> map);

	List<Map<String, Object>> friendsList(Map<String, Object> map);
	
	int friendAdd(Map<String, Object> map);
	
	int updatePerInfo(Map<String, Object> map);
	
	int friendDel(Map<String, Object> map);

	int favouriteDel(Map<String, Object> map);
	
	List<Map<String, Object>> favouriteList(Map<String, Object> map);
	
	int infoDel(Map<String, Object> map);
	
	int totalGreat(Map<String, Object> map);
	
	Map<String, Object> personInfo(Map<String, Object> map);
	
	List<LikeType> selectLike();
}