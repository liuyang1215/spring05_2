package cn.tedu.ems.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.ems.dao.EmployDAO;
import cn.tedu.ems.entity.Employee;

@Controller
public class EmpListController {
		
	@Resource(name="employDAO")
	private EmployDAO employDAO;
	

	@RequestMapping("/toList.do")
	public String toList() {
		System.out.println("toList()");
		return "list";
	}
	
	@RequestMapping("/list.do")
	public String listEmp(ModelMap mm) {
		System.out.println("listEmp()");
		List<Employee> e = employDAO.findAll();
		System.out.println(e);
		mm.addAttribute("emp",e);
		return "list";
	}
	
	@RequestMapping("/toAdd.do")
	public String toAdd() {
		System.out.println("toAdd()");
		return "add";
	}
	
	@RequestMapping("/add.do")
	public String addEmp(HttpServletRequest request,Employee emp) {
		String name = request.getParameter("uname");
		String sal = request.getParameter("salary");
		String age = request.getParameter("age");

		emp.setName(name);
		emp.setSalary(Double.parseDouble(sal));
		emp.setAge(Integer.parseInt(age));
		employDAO.save(emp);
		return "redirect:list.do";
	}
	
	@RequestMapping("/del.do")
	public String del(HttpServletRequest request) {
		String id = request.getParameter("id");
		employDAO.delete(Integer.parseInt(id));
		return "redirect:list.do";
	}
	
	@RequestMapping("/load.do")
	public String load(HttpServletRequest request,ModelMap mm) {
		String id = request.getParameter("id");
		Employee employee = employDAO.findById(Integer.parseInt(id));
		mm.addAttribute("employee",employee);
		return "update";
	}
	
	@RequestMapping("/update.do")
	public String update(HttpServletRequest request,Employee e) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sal = request.getParameter("salary");
		String age = request.getParameter("age");
		
		e.setId(Integer.parseInt(id));
		e.setName(name);
		e.setSalary(Double.parseDouble(sal));
		e.setAge(Integer.parseInt(age));
		employDAO.update(e);
		return "redirect:list.do";
	}
	

}
