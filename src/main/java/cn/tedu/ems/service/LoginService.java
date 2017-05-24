package cn.tedu.ems.service;

import cn.tedu.ems.entity.User;

/**
 * 业务层接口
 * @author soft01
 *
 */
public interface LoginService {
	//用于处理登录
	public User checkLogin(String username,String pwd);

}
