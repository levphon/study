package spring.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jdbc.domain.Dept;
import spring.jdbc.domain.mapper.DeptMapper;

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

}
