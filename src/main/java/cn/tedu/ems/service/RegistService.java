package cn.tedu.ems.service;
import javax.servlet.http.HttpServletRequest;

/**
 * 此类用于处理用户注册
 */
import cn.tedu.ems.entity.User;

public interface RegistService {
	public User checkRegist(String username,HttpServletRequest request);
}
