package cn.tedu.ems.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.ems.dao.UserDAO;
import cn.tedu.ems.entity.User;
import cn.tedu.ems.service.ApplicationException;
import cn.tedu.ems.service.LoginService;
import cn.tedu.ems.service.RegistService;

@Controller
public class RegistController {
	
	@Resource(name="registService")
	private RegistService registService;
	
	
	@RequestMapping("/toRegist.do")
	public String toRegist() {
		System.out.println("toRegist()");
		return "regist";
	}
	
	@ExceptionHandler
	public String HandlerException (HttpServletRequest request,Exception e) {
		if(e instanceof ApplicationException) {
			request.setAttribute("login2_failed", e.getMessage());
			return "regist";
		}
		return "error";
	}
	
	@RequestMapping("/regist.do")
	public String regist(HttpServletRequest request,HttpSession session) {
		System.out.println("regist()");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		User user = registService.checkRegist(username, request);
		session.setAttribute("user", user);
		return "redirect:login.do";
	}	

}
