package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ems.dao.UserDAO;
import cn.tedu.ems.entity.User;

public class TestCase2 {
	@Test
	public void test1() {
		String config = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		UserDAO dao = ac.getBean("userDAO",UserDAO.class);
		User user = dao.findByUsername("sally");
		System.out.println(user);
	}
	
	@Test
	public void test2() {
		String config = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		UserDAO dao = ac.getBean("userDAO",UserDAO.class);
		User user = new User();
		user.setUsername("emp11");
		user.setPwd("1234");
		dao.save(user);
	}

}
