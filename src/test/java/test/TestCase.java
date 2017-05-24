package test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ems.dao.EmployDAO;
import cn.tedu.ems.entity.Employee;

public class TestCase {
	
	private EmployDAO employDAO;
	
	@Before
	public void init() {
		String config = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		employDAO = ac.getBean("employDAO",EmployDAO.class);
	}
	
	@Test
	public void test1() throws SQLException {
		String config = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		/*
		 * DataSource是一个接口,BasicDataSource实现了该接口.
		 */
		DataSource ds = ac.getBean("ds",DataSource.class);
		System.out.println(ds.getConnection());
		
	}
	
	@Test
	public void test2() {
		List<Employee> employee = employDAO.findAll();
		System.out.println(employee);
	}
	
	@Test
	public void test3() {
		Employee emp = employDAO.findById(321);
		System.out.println(emp);
		
	}
	
	@Test
	public void test4() {
		Employee emp = new Employee();
		emp.setName("小水");
		emp.setSalary(12000);
		emp.setAge(10);
		employDAO.save(emp);	
	}
	

}
