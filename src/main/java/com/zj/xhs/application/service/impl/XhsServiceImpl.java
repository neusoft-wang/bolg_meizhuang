package com.zj.xhs.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.person.application.mapper.PersonMapper;
import com.zj.person.domain.LikeType;
import com.zj.xhs.application.mapper.XhsMapper;
import com.zj.xhs.application.service.XhsService;
import com.zj.xhs.domain.Info;

@Service
public class XhsServiceImpl implements XhsService {
	
	@Autowired
	private XhsMapper xhsMapper;

	@Override
	public int newInfo(Map<String, Object> map) {
		
		return xhsMapper.newInfo(map);
	}

	@Override
	public List<Info> selectInfo(Map<String, Object> map) {

		return xhsMapper.selectInfo(map);
	}

	@Override
	public int selectCountInfo(Map<String, Object> map) {
		
		return xhsMapper.selectCountInfo(map);
	}

	@Override
	public int insertImage(List<Map<String,Object>> list) {
		
		xhsMapper.updateFImages(list.get(0));
		return xhsMapper.insertImage(list);
	}

	@Override
	public Map<String, Object> infoDetail(String param) {
		
		return xhsMapper.infoDetail(param);
	}

	@Override
	public List<Map<String,Object>> infoComment(Map<String, Object> param) {
		
		return xhsMapper.infoComment(param);
	}

	@Override
	public int buildComment(Map<String, Object> map) {
		
		return xhsMapper.buildComment(map);
	}

	@Override
	public Map<String, Object> bool(Map<String, Object> map) {
		
		map.put("boolGreat", xhsMapper.boolGreat(map)==0?false:true);
		map.put("boolFavourite", xhsMapper.boolFavourite(map)==0?false:true);
		
		return map;
	}

	@Override
	public int greatAndFavourite(Map<String, Object> map) {
		
		int flag = 0;
		if ("great".equals(map.get("type"))) {
			flag = xhsMapper.insertGreat(map);
		}else{
			flag = xhsMapper.insertFavourite(map);
		}
		
		return flag;
	}
	
	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public String selectLike() {
		List<LikeType> temp = personMapper.selectLike();
		StringBuffer sb = new StringBuffer();
		String a = "<option value=\"";
		String b = "\">";
		String c = "</option>";
		for(LikeType item:temp) {
			sb.append(a).append(item.getId()).append(b).append(item.getType()).append(c);
		}
		return sb.toString();
	}
}