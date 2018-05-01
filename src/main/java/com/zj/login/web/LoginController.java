package com.zj.login.web;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONObject;

import com.zj.core.BaseController;
import com.zj.login.application.service.LoginService;
import com.zj.login.domain.Login;
import com.zj.person.application.mapper.PersonMapper;
import com.zj.person.domain.LikeType;
import com.zj.utils.CommonConstant;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	public static String LOGIN_SYSTEM_INFO = "LOGIN_USER_INFO";
	public static String PERSON_LIKE_TYPE = "PERSON_LIKE_TYPE";

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/page")
	public String loginPage(HttpServletRequest request,HttpServletResponse response) {
		
		return "/login";
	}
	
	@RequestMapping(value = "/Rpage")
	public String Rpage(HttpServletRequest request,HttpServletResponse response) {
		
		return "redirect:/login/page";
	}
	
	@RequestMapping(value = "/main", method=RequestMethod.POST)
	public String loginMain(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {

		JSONObject jsonObject = new JSONObject();
		Boolean resFlag = false;
		Login loginUser = null;
		loginUser =	loginService.selectUser(map);
		if(null != loginUser){
			if (loginUser.getIsType()==0) {
				List<String> list = new ArrayList<String>();
				if (null==loginUser.getLikeType()) {
					list = null;
				}else {
					list = Arrays.asList(loginUser.getLikeType().split(","));
				}
				request.getSession().setAttribute(LoginController.PERSON_LIKE_TYPE, list);
			}
			
			request.getSession().setAttribute(LoginController.LOGIN_SYSTEM_INFO, loginUser);

			resFlag = true;
			jsonObject.put("isType", loginUser.getIsType());
		}
		
		jsonObject.put("flag", resFlag);
		renderJson(request, response, jsonObject);
		return null;
	}
	
	@RequestMapping(value = "/registPage")
	public String registPage(HttpServletRequest request,HttpServletResponse response) {
		
		return "/regist";
	}
	
	@RequestMapping(value = "/regist", method=RequestMethod.POST)
	public String regist(HttpServletRequest request,HttpServletResponse response,
			@RequestBody Map<String, Object> map) {
		
		JSONObject jsonObject = new JSONObject();
		Boolean resFlag = false;
		String msg = "";
		if(loginService.registNewOne(map)>0){
			resFlag = true;
			request.getSession().setAttribute(LoginController.LOGIN_SYSTEM_INFO, loginService.selectUser(map));
		}else{
			msg = "您输入的帐号已经存在";
		}
		jsonObject.put("flag", resFlag);
		jsonObject.put("msg", msg);
		renderJson(request, response, jsonObject);
		return null;
	}
	
	@RequestMapping(value = "/headIcon")
	public String headIcon(HttpServletRequest request,HttpServletResponse response) {
		
		return "/addHeadIcon";
	}
	
	@RequestMapping(value = "/subHeadIcon", method=RequestMethod.POST)
	public String subHeadIcon(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("image") String base64Data) throws Exception {
		
		logger.info("上传头像:~~~~~~~~~"+base64Data);
		
		Login login = getUserInfo(request);
        String tempFileName = UUID.randomUUID().toString()+CommonConstant.IMAGES_HEAD+CommonConstant.IMAGES_FORMAT;
        String filePath = CommonConstant.IMAGES_PATH;
        String data = "";
        if(base64Data == null || "".equals(base64Data)){
            throw new Exception("上传失败，上传图片数据为空");
        }else{
            String [] d = base64Data.split("base64,");
            if(d != null && d.length == 2){
                data = d[1];
            }else{
                throw new Exception("上传失败，数据不合法");
            }
        }
        
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
        	targetFile.mkdirs();    
        }
        
      //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
        byte[] bs = Base64Utils.decodeFromString(data);
        boolean resFlag = false;
        try {
        	FileOutputStream out = new FileOutputStream(filePath+tempFileName);
        	out.write(bs);
        	out.flush();
        	out.close();
        	login.setHeadIcon(CommonConstant.SLASH+tempFileName);
        	if (loginService.updateHeadIcon(login)>0) {
            	resFlag = true;
			}
		} catch (Exception e) {
			
		}
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", resFlag);
		renderJson(request, response, jsonObject);
        
		return null;
	}
	
}
