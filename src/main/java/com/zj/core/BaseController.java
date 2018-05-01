package com.zj.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zj.login.domain.Login;
import com.zj.login.web.LoginController;

/**
 * @ClassName: BaseController
 */
public abstract class BaseController {
	
	protected String renderJson(final HttpServletRequest request, final HttpServletResponse response, final JSONObject object) {
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {

		}
		return null;
	}

	protected String renderJsonArray(final HttpServletRequest request, final HttpServletResponse response, final JSONArray object) {
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {

		}
		return null;
	}

	protected Login getUserInfo(HttpServletRequest request) {

		Login u = (Login) request.getSession().getAttribute(LoginController.LOGIN_SYSTEM_INFO);
		return u;
	}
	
	protected List<String> getLikeType(HttpServletRequest request) {
		
		List<String> u = (List<String>) request.getSession().getAttribute(LoginController.PERSON_LIKE_TYPE);
		return u;
	}

}