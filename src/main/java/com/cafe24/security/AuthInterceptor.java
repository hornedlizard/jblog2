package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog2.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// handler가 handlerMethod인지
		if (handler instanceof HandlerMethod == false) {
			return false;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// Auth 받기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// method에 @Auth가 없을 때
		if (auth == null) {
			return true;
		}
		
		// @Auth가 있는 경우 session 확인
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		Map<String, String> pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if (!(pathVariables.get("id").equals(authUser.getId()))) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		return true;
	}

}
