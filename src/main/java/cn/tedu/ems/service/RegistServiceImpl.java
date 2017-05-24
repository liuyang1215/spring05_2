package cn.tedu.ems.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.tedu.ems.dao.UserDAO;
import cn.tedu.ems.entity.User;
@Service("registService")
public class RegistServiceImpl implements RegistService {
	
	@Resource(name="userDAO")
	private UserDAO dao;

	public User checkRegist(String username, HttpServletRequest request) {
		User user = null;
		username = request.getParameter("username");
		user = dao.findByUsername(username);
		if( user != null) {
			throw new ApplicationException("用户名已存在");
		} else {
			String pwd = request.getParameter("pwd");
			user = new User();
			user.setUsername(username);
			user.setPwd(pwd);
			dao.save(user);
		}
		return user;

	}

}
