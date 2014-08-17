package spring;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class SpringTest {

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:spring/beans.xml");
		System.out.println(context);
		context.close();
	}

	@Test
	public void testApplicationContext() {
		System.out.println(context);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = context.getBean("dataSource", DataSource.class);
		System.out.println(dataSource.getConnection());
	}

	@Test
	public void testJdbcTemplate() {
		JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
		String sysdate = jdbcTemplate.queryForObject("select sysdate from dual", String.class);
		System.out.println(sysdate);
	}

}
