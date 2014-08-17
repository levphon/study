package spring.javaconfig;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfiguration.class })
public class JavaconfigTest {

	@Inject
	private ApplicationContext context;

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
		System.out.println(context);
		context.close();
	}

	@Test
	public void testApplicationContext() {
		System.out.println(context);
	}

	@Test
	public void testJdbcTemplate() {
		JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
		String sysdate = jdbcTemplate.queryForObject("select sysdate from dual", String.class);
		System.out.println(sysdate);
	}

}
