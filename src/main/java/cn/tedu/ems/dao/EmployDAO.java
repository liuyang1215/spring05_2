package cn.tedu.ems.dao;

import java.util.List;

import cn.tedu.ems.entity.Employee;

public interface EmployDAO {
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee emp);
	
	public void update(Employee emp);
	
	public void delete(int id);
	
	

}
