package spring.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jdbc.domain.Dept;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class SpringJdbcTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testGetOneDept() {
		String sql = "select * from dept d where d.deptno = ?";
		Dept dept = this.jdbcTemplate.queryForObject(sql, Dept.class, "10");
		System.out.println(dept);
	}

}
