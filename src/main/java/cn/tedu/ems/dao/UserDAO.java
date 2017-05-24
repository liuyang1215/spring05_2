package cn.tedu.ems.dao;

import cn.tedu.ems.entity.User;

public interface UserDAO {
	public User findByUsername(String username);
	
	public void save(User user);

}
