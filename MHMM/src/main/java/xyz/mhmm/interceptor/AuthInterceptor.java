package xyz.mhmm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import xyz.mhmm.utils.SessionAttribute;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		
		if (session.getAttribute(SessionAttribute.USER_NO) == null) {
			response.sendRedirect("/auth/login");
			return false;
		}

		return true;
	}

}
