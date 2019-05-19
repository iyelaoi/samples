package wqz.handlers;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginHandler implements HandlerInterceptor{
	static{
		for(int i = 0; i < 10 ; i++){
			System.out.println("*******************************************");
		}
	}
	public LoginHandler() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("login handler");
		String uri = request.getRequestURI();
		System.out.println("uri " + uri);
		if(uri.endsWith("login") || uri.endsWith("regist")){
			return true;
		}
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();		
		if(session.getAttribute("user") != null){
			return true;
		}
		System.out.println("login hadler --> despather");
		System.out.println("contextPath" + contextPath);
		request.getRequestDispatcher("/WEB-INF/views/loginpage.jsp").forward(request, response);
		return false;
	}

}
