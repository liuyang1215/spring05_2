package cn.tedu.ems.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.tedu.ems.entity.Employee;
/**
 * 此类是用来测试JdbcTemplate，测试通过
 * 后持久层代码改用mybatis写，所以此类可以不看
 * @author txgcwm
 *
 */

//@Repository("employDAO")
public class EmployDAOImpl implements EmployDAO{
	
	@Resource(name="jt")
	private JdbcTemplate jt;

	public List<Employee> findAll() {
		String sql = "SELECT * FROM emps_lyy";
		return jt.query(sql, new EmpRowMapper());
	}

	
	public Employee findById(int id) {
		String sql = "SELECT * FROM emps_lyy WHERE id = ?";
		Object[] args = {id};
		Employee e = null;
		try {
			e = jt.queryForObject(sql, args,new EmpRowMapper());
		} catch(EmptyResultDataAccessException e1) {
			e1.printStackTrace();
		}
		return e;
	}

	public void save(Employee emp) {
		String sql = "INSERT INTO emps_lyy VALUES(emps_seq_ly.nextval,?,?,?)";
		Object[] args = {emp.getName(),emp.getSalary(),emp.getAge()};
		jt.update(sql, args);
		
	}

	public void update(Employee emp) {
		String sql = "UPDATE emps_lyy SET name=? ,sal = ?,age=? WHERE id=?";
		Object[] args = {emp.getName(),emp.getSalary(),emp.getAge(),emp.getId()};
		jt.update(sql, args);
	}

	public void delete(int id) {
		String sql = "DELETE FROM emps_lyy WHERE id = ?";
		Object[] args = {id};
		jt.update(sql, args);
	}
	
	class EmpRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int index) throws SQLException {
			Employee e = new Employee();
			e.setId(rs.getInt("id"));
			e.setName(rs.getString("name"));
			e.setSalary(rs.getDouble("salary"));
			e.setAge(rs.getInt("age"));
			return e;
		}
		
	}

}
