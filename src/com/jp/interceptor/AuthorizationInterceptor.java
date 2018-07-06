package com.jp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器必须实现HandlerInterceptor接口
 * @author Administrator
 *
 */
public class AuthorizationInterceptor implements HandlerInterceptor{
	//不拦截"/loginForm","/login"的请求
	private static final String[] IGNORE_URI = {"/loginForm", "/login"};
	
	public AuthorizationInterceptor() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 该方法在整个请求完成后执行，主要作用是清理资源
	 * 该方法也只会在当前Interceptor的preHandle方法的返回值为true时才会执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AuthorizationInterceptor afterCompletion......");
	}
	/**
	 * 该方法将在Controller的方法调用后执行，方法中可以对ModelAndView进行操作
	 * 该方法也只会在preHanle方法返回值为true时才会执行
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AuthorizationInterceptor postHandle......");
	}
	/**
	 * 该方法是进行处理器拦截用的，在Controller处理之前进行调用
	 * 返回true拦截器才会往下执行，false则整个请求结束
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AuthorizationInterceptor preHandle......");
		boolean flag = false;
		//获取请求路径进行判断
		String servletPath = request.getServletPath();
		for(String s:IGNORE_URI){
			if(servletPath.contains(s)){
				flag = true;
				System.out.println("AuthorizationInterceptor 不拦截......" + servletPath);
				break;
			}
		}
		//拦截请求
		if(!flag){
			System.out.println("AuthorizationInterceptor 拦截判断......" + servletPath);
			/*UserCustom user = (UserCustom)request.getSession().getAttribute("user");
			//判断用户是否已经登陆
			if(user==null){
				System.out.println("AuthorizationInterceptor 拦截......" + servletPath);
				request.setAttribute("message", "请先登陆再访问网站");
				request.getRequestDispatcher("/loginForm").forward(request, response);
			}else{//已经登陆
				System.out.println("AuthorizationInterceptor 放行......" + servletPath);
				flag=true;
			}*/
		}
		//eturn flag;
		return true;
		
	}

}
