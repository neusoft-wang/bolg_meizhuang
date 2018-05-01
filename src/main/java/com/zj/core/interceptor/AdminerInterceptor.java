package com.zj.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zj.login.domain.Login;
import com.zj.login.web.LoginController;

public class AdminerInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		logger.info(request.getRequestURL() + "system拦截器###############:" + request.getSession().getAttribute(LoginController.LOGIN_SYSTEM_INFO));
		
		// 判断是否有注册信息
		if (null == request.getSession().getAttribute(LoginController.LOGIN_SYSTEM_INFO)) {
			response.sendRedirect(request.getContextPath() + "/login/page");
			return false;
		}
		Login login = (Login) request.getSession().getAttribute(LoginController.LOGIN_SYSTEM_INFO);
		if (0==login.getIsType()) {
			response.sendRedirect(request.getContextPath() + "/xhs/list");
		}
		return true;
	}
}
