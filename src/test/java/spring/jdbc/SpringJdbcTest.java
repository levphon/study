package spring.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jdbc.domain.Dept;
import spring.jdbc.domain.Emp;
import spring.jdbc.domain.mapper.DeptMapper;
import spring.jdbc.domain.mapper.EmpMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class SpringJdbcTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testGetOneDept() {
		String sql = "select * from dept d where d.deptno = ?";
		Dept dept = this.jdbcTemplate.queryForObject(sql, new DeptMapper(), 10);
		System.out.println(dept);
	}

	@Test
	public void testGetAllDepts() {
		String sql = "select * from dept";
		List<Dept> depts = this.jdbcTemplate.query(sql, new DeptMapper());
		System.out.println(depts);
	}

	@Test
	public void testGetOneEmp() {
		String sql = "select * from emp e left join dept d on e.deptno = d.deptno where e.empno = ?";
		Emp emp = this.jdbcTemplate.queryForObject(sql, new EmpMapper(), 7788);
		System.out.println(emp);
	}

	@Test
	public void testGetAllEmps() {
		String sql = "select * from emp e left join dept d on e.deptno = d.deptno";
		List<Emp> emps = this.jdbcTemplate.query(sql, new EmpMapper());
		System.out.println(emps);
	}

}
