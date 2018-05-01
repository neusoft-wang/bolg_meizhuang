package com.zj.xhs.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zj.adminer.application.mapper.AdminerMapper;
import com.zj.core.BaseController;
import com.zj.login.domain.Login;
import com.zj.person.application.mapper.PersonMapper;
import com.zj.person.domain.LikeType;
import com.zj.utils.CommonConstant;
import com.zj.xhs.application.service.XhsService;
import com.zj.xhs.domain.Info;

@Controller
@RequestMapping("/xhs")
public class XhsController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private XhsService xhsService;
	
	@Autowired
	private AdminerMapper adminerMapper;
	
	@RequestMapping(value = "/list")
	public String ListPage(HttpServletRequest request,HttpServletResponse response) {
		
		Login login = getUserInfo(request);
		request.setAttribute("headIcon", login.getHeadIcon());
		request.setAttribute("nickName", login.getNickName());
		request.setAttribute("userId", login.getUserId());

		return "/list";
	}
	
	@RequestMapping(value = "/topList",method=RequestMethod.GET)
	public String adminList(HttpServletRequest request,HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		Map<String,Object> param = new HashMap<String,Object>();
		List<Map<String, Object>> resMap = adminerMapper.top1List();
		if(resMap.size()<6) {
			param.put("page", 6-resMap.size());
			resMap.addAll(adminerMapper.buquanTop(param));
		}
		jsonObject.put("toplist", resMap);
		renderJson(request, response, jsonObject);		
		return null;
	}

	@RequestMapping(value = "/NewInfoPage")
	public String NewInfoPage(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("selectLike", xhsService.selectLike());
		return "/newinfo";
	}
	
	@RequestMapping(value = "/PersonPage")
	public String PersonPage(HttpServletRequest request,HttpServletResponse response) {
		return "/person";
	}
	
	@RequestMapping(value = "/Info/{infoId}")
	public String InfoPage(@PathVariable String infoId,HttpServletRequest request,HttpServletResponse response,Model model) {
		
		Login login = getUserInfo(request);
		int userId = login.getUserId();
		request.setAttribute("headIcon", login.getHeadIcon());
		request.setAttribute("nickName", login.getNickName());
		request.setAttribute("userId", userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoId", infoId);
		map.put("userId", userId);
		
		Map<String,Object> result = xhsService.infoDetail(infoId);
		result.put("infoId", infoId);
		List<String> imglist = Arrays.asList(result.get("url").toString().split(","));
		List<Map<String, Object>> listComment = xhsService.infoComment(map);
		
		String boolGreat = "";
		String boolFavourite = "";
		Long tempUserId = (Long) result.get("userId");
		if(userId!=tempUserId){
			if(!(boolean) xhsService.bool(map).get("boolGreat")){
				boolGreat = "<button class=\"btn\" id=\"great\" onclick=\"user('great')\">点赞</button>";
			}else{
				boolGreat = "<button class=\"btn\" disabled=\"disabled\">已点赞</button>";
			}
			
			if(!(boolean) xhsService.bool(map).get("boolFavourite")){
				boolFavourite = "<button class=\"btn\" id=\"favourite\" onclick=\"user('favourite')\">收藏</button>";
			}else{
				boolFavourite = "<button class=\"btn\" disabled=\"disabled\">已收藏</button>";
			}
		}
		model.addAttribute("list", imglist);
		model.addAttribute("detail", result);
		model.addAttribute("boolGreat", boolGreat);
		model.addAttribute("boolFavourite", boolFavourite);
		model.addAttribute("listComment", listComment);
		
		return "/info";
	}
	
	@RequestMapping(value = "/newInfo", method=RequestMethod.POST)
	public String loginMain(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		Boolean resFlag = false;
		int userId = getUserInfo(request).getUserId();
		map.put("userId", userId);
		map.put("headIcon", "/zj/images/logo.jpg");
		if(xhsService.newInfo(map)>0){
			resFlag = true;
		}
		jsonObject.put("flag", resFlag);
		jsonObject.put("infoId", map.get("infoId"));
		renderJson(request, response, jsonObject);
		
		return null;
	}
	
	@RequestMapping(value = "/selectList", method=RequestMethod.POST)
	public String searchList(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		List temp = getLikeType(request);
		map.put("likeType", temp);
		
		List<Info> list = xhsService.selectInfo(map);
		
		jsonObject.put("IsNull", list.size()>0);
		jsonObject.put("list", list);
		renderJson(request, response, jsonObject);

		return null;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/subInfoImages", method=RequestMethod.POST)
//	public String subHeadIcon(HttpServletRequest request,HttpServletResponse response,
//			@RequestParam(value="file",required= false) MultipartFile[] files) throws Exception {
	public String subHeadIcon(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);    
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		
        JSONObject jsonObject = new JSONObject();
        
		String filePath = CommonConstant.IMAGES_PATH;
		int infoId = Integer.valueOf(params.getParameter("infoId"));
        String tempFileName = "";
        List<Map<String,Object>> paramList = new ArrayList<Map<String,Object>>();
        MultipartFile file = null;
        try {
        	if(files!=null&&files.size()>0){  
                for(int i = 0;i<files.size();i++){
                	Map<String,Object> temp = new HashMap<String, Object>();
                	tempFileName = UUID.randomUUID().toString()+CommonConstant.IMAGES_INFO+CommonConstant.IMAGES_FORMAT;
                    file = files.get(i);
                    file.transferTo(new File(filePath+tempFileName));
                    temp.put("infoId", infoId);
                    temp.put("fileName", CommonConstant.SLASH+tempFileName);
                    paramList.add(temp);
                }  
            }
        	
        	if (xhsService.insertImage(paramList)==paramList.size()) {
        		jsonObject.put("flag", true);
			}
		} catch (Exception e) {
			logger.error("咖喱给gay！！");
			e.printStackTrace();
	        jsonObject.put("flag", "");
		}
        
		renderJson(request, response, jsonObject);
		return null;
	}
	
	@RequestMapping(value = "/infoComment", method=RequestMethod.POST)
	public String infoComment(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		List<Map<String, Object>> list = xhsService.infoComment(map);
		
		jsonObject.put("list", list);
		renderJson(request, response, jsonObject);

		return null;
	}
	
	@RequestMapping(value = "/buileComment", method=RequestMethod.POST)
	public String buileComment(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		int userId = getUserInfo(request).getUserId();
		map.put("userId", userId);
		if(xhsService.buildComment(map)>0){
			jsonObject.put("flag", true);
		}

		renderJson(request, response, jsonObject);
		return null;
	}
	
	@RequestMapping(value = "/greatAndFavourite", method=RequestMethod.POST)
	public String greatAndFavourite(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		
		int userId = getUserInfo(request).getUserId();
		map.put("userId", userId);
		
		if(xhsService.greatAndFavourite(map)>0){
			jsonObject.put("flag", true);
		}

		renderJson(request, response, jsonObject);
		return null;
	}
}
