package com.zj.person.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zj.core.BaseController;
import com.zj.login.domain.Login;
import com.zj.login.web.LoginController;
import com.zj.person.application.mapper.PersonMapper;
import com.zj.person.application.service.PersonService;
import com.zj.xhs.application.service.XhsService;
import com.zj.xhs.application.service.impl.XhsServiceImpl;
import com.zj.xhs.web.XhsController;

@Controller
@RequestMapping("/person")
public class PersonController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private XhsService xhsService;
	
	@RequestMapping(value = "/favourite/{userId}")
	public String favouritePage(@PathVariable int userId,HttpServletRequest request,HttpServletResponse response,Model model,Map<String, Object> param) {
		
		Login login = getUserInfo(request);
		request.setAttribute("headIcon", login.getHeadIcon());
		request.setAttribute("nickName", login.getNickName());
		request.setAttribute("userId", login.getUserId());
		
		param.put("userId", userId);
		model.addAttribute("infosList", personService.favouriteDetail(param));
		
		return "/favourite";
	}
	
	@RequestMapping(value = "/Info/{userId}")
	public String PersonPage(@PathVariable int userId,HttpServletRequest request,HttpServletResponse response,Model model) {
		
		String urlPage = "/personMe";
		Login login = getUserInfo(request);
		request.setAttribute("headIcon", login.getHeadIcon());
		request.setAttribute("nickName", login.getNickName());
		request.setAttribute("userId", login.getUserId());
		if(0==userId){
			userId = login.getUserId();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		if(login.getUserId()!=userId){
			urlPage = "/person";
			map.put("isCheck", 1);
			map.put("myUserId", login.getUserId());
			request.setAttribute("thePerId", userId);
		}
		
		Map<String, Object> resultMap = personService.personDetail(map);
		
		model.addAttribute("personDetail", resultMap.get("personDetail"));
		model.addAttribute("infosList", resultMap.get("infosList"));
		model.addAttribute("friendsList", resultMap.get("friendsList"));
		
		return urlPage;
	}
	
	@RequestMapping(value = "/updateInfo")
	public String UpdatePage(HttpServletRequest request,HttpServletResponse response,Model model) {

		model.addAttribute("personDetail", getUserInfo(request));
		model.addAttribute("selectLike", xhsService.selectLike());
		
		return "/perUpdate";
	}
	
	@RequestMapping(value = "/updatePerInfo", method=RequestMethod.POST)
	public String updatePerInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		map.put("userId", getUserInfo(request).getUserId());
		ArrayList<String> temp = (ArrayList<String>) map.get("likeType");
		String[] strings = new String[temp.size()];
		temp.toArray(strings);
		map.put("likeType", StringUtils.join(strings, ","));
		jsonObject.put("flag", personService.updatePerInfo(map)>0?true:false);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/friendAdd", method=RequestMethod.POST)
	public String friendAdd(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		Login login = getUserInfo(request);
		map.put("userId", login.getUserId());
		
		jsonObject.put("flag", personService.friendAdd(map)>0?true:false);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/LikeType", method=RequestMethod.GET)
	public String selectLike(HttpServletRequest request,HttpServletResponse response) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("likeType", getLikeType(request));
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/friendDel", method=RequestMethod.POST)
	public String friendDel(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		Login login = getUserInfo(request);
		map.put("userId", login.getUserId());
		
		jsonObject.put("flag", personService.friendDel(map)>0?true:false);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/infoDel", method=RequestMethod.POST)
	public String infoDel(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", personService.infoDel(map)>0?true:false);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/favouriteDel", method=RequestMethod.POST)
	public String favouriteDel(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", personService.favouriteDel(map)>0?true:false);
		renderJson(request, response, jsonObject);		
		return null;
	}
	
	@RequestMapping(value = "/infoSelect", method=RequestMethod.POST)
	public String infoSelect(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		Login login = getUserInfo(request);
		map.put("userId", login.getUserId());
		jsonObject.put("list", personService.favouriteDetail(map));
		renderJson(request, response, jsonObject);		
		return null;
	}
}
