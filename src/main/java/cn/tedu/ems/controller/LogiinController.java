package cn.tedu.ems.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.xml.internal.ws.handler.HandlerException;

import cn.tedu.ems.entity.User;
import cn.tedu.ems.service.ApplicationException;
import cn.tedu.ems.service.LoginService;

@Controller
public class LogiinController {
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		System.out.println("toLogin()");
		return "login";
	}
	
	@ExceptionHandler
	public String HandlerException (HttpServletRequest request,Exception e) {
		if(e instanceof ApplicationException) {
			request.setAttribute("login_failed", e.getMessage());
			return "login";
		}
		return "error";
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpSession session) {
		System.out.println("login()");
		//读取用户名和密码
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		System.out.println(username +" " +pwd);
		//调用业务层的功能
		//注:需要处理业务层所抛出的异常,将异常转换成用户能理解的信息
		//系统异常:可以提示用户稍后重试
		//应用异常:明确提示用户采取正确的操作

		User user = loginService.checkLogin(username, pwd);	
		//登录成功,将user绑定到session对象上,用于session验证
		session.setAttribute("user", user);
		//登录成功,跳转到首页
		return "redirect:list.do";
	}	
}
