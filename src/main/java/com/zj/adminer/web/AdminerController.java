package com.zj.adminer.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zj.adminer.application.mapper.AdminerMapper;
import com.zj.adminer.application.service.AdminerService;
import com.zj.core.BaseController;
import com.zj.login.domain.Login;

@Controller
@RequestMapping("/adminer")
public class AdminerController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminerService adminerService;
	
	@Autowired
	private AdminerMapper adminerMapper;
	
	@RequestMapping(value = "/list")
	public String adminPage(HttpServletRequest request,HttpServletResponse response,Model model,Map<String, Object> param) {
		
		Login login = getUserInfo(request);
		request.setAttribute("adminName", login.getNickName());
		return "/adminList";
	}
	
	@RequestMapping(value = "/top6")
	public String lunPage(HttpServletRequest request,HttpServletResponse response,Model model,Map<String, Object> param) {
		
		Login login = getUserInfo(request);
		request.setAttribute("adminName", login.getNickName());
		return "/adminTop";
	}
	
	@RequestMapping(value = "/tabPage")
	public String tabPage(HttpServletRequest request,HttpServletResponse response,Model model,Map<String, Object> param) {
		
		Login login = getUserInfo(request);
		request.setAttribute("adminName", login.getNickName());
		return "/adminTab";
	}
	
	@RequestMapping(value = "/selectList")
	public String adminList(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", adminerService.list(map));
		jsonObject.put("toplist", adminerService.topList(map));
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/selectTab")
	public String selectTab(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tabList", adminerMapper.tabList(map));
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/addTop6")
	public String addTop6(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", adminerService.addTop6(map)>0);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/Info/{infoId}/{test}")
	public String InfoPage(@PathVariable String infoId,@PathVariable String test,HttpServletRequest request,HttpServletResponse response,Model model) {
		
		Login login = getUserInfo(request);
		request.setAttribute("adminName", login.getNickName());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoId", infoId);
		map.putAll(adminerService.infoDetail(infoId));;
		if(null!=map.get("url")) {
			List<String> imglist = Arrays.asList(map.get("url").toString().split(","));
			model.addAttribute("list", imglist);
		}
		
		model.addAttribute("top6", "1".equals(test));
		model.addAttribute("detail", map);
		return "/adminInfo";
	}
	
	@RequestMapping(value = "/infoIscheck", method=RequestMethod.POST)
	public String infoIsCheck(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("flag", adminerService.infoIsCheck(map)>0?true:false);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/outTop6", method=RequestMethod.POST)
	public String outTop(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("flag", adminerMapper.outTop6(map)>0);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/checkTab", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkTab(@RequestBody Map<String, Object> map) {
		
		map.put("flag", null!=adminerMapper.checkTab(map));
		
		return map;
	}
	
	@RequestMapping(value = "/addTabJ", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTab(@RequestBody Map<String, Object> map) {
		if ((int)map.get("db")!=0) {
			map.put("flag",adminerMapper.updateTabDD(map)>0);
		}else {
			if(null==adminerMapper.checkTab(map)) {
				map.put("flag", adminerMapper.addTab(map)>0);
			}else {
				map.put("flag", adminerMapper.updateTab(map)>0);
			}
		}
		return map;
	}
	
	@RequestMapping(value = "/outTab", method=RequestMethod.POST)
	public String outTab(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("flag", adminerMapper.outTab(map)>0);
		renderJson(request, response, jsonObject);		
		return null;
	}
}
