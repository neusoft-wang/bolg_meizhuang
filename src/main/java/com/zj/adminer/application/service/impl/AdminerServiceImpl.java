package com.zj.adminer.application.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.adminer.application.mapper.AdminerMapper;
import com.zj.adminer.application.service.AdminerService;

@Service
public class AdminerServiceImpl implements AdminerService {

	@Autowired
	private AdminerMapper adminerMapper;

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		if(null!=map.get("topInfo")) {
			return adminerMapper.top0List(map);
		}
		return adminerMapper.list(map);
	}
	
	@Override
	public List<Map<String, Object>> topList(Map<String, Object> map) {

		return adminerMapper.top1List();
	}
	
	@Override
	public Map<String, Object> infoDetail(String id) {
		
		return adminerMapper.infoDetail(id);
	}

	@Override
	public int infoIsCheck(Map<String, Object> map) {

		return adminerMapper.infoIsCheck(map);
	}

	@Override
	public int addTop6(Map<String, Object> map) {
		Map<String, Object> temp = adminerMapper.selectTop(map);
		if (null!=temp) {
			Map<String, Object> selectMap = new HashMap<String, Object>();
			//更换已有top
			selectMap.put("infoid", temp.get("infoId"));
			selectMap.put("selectId", map.get("currId"));
			adminerMapper.addTop6(selectMap);
		}
		return adminerMapper.addTop6(map);
	}
}