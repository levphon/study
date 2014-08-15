package spring;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class SpringTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
		logger.debug("applicationContext:{}", context);
	}

	@Test
	public void testApplicationContext() {
		logger.debug("applicationContext:{}", context);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = context.getBean("dataSource", DataSource.class);
		logger.debug("dataSource.connection:{}", dataSource.getConnection());
	}

	@Test
	public void testJdbcTemplate() {
		JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
		String sysdate = jdbcTemplate.queryForObject("select sysdate from dual", String.class);
		logger.debug("Oracle sysdate:{}", sysdate);
	}

}
