package spring.javaconfig;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rest.resteasy.shop.domain.Customer_xml;

@ActiveProfiles("java")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfiguration.class })
public class SpringJavaconfigTest {

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

	@Test
	public void testJaxb2() throws XmlMappingException, IOException {
		Jaxb2Marshaller marshaller = context.getBean("marshaller", Jaxb2Marshaller.class);
		marshaller.marshal(new Customer_xml(), new StreamResult(System.out));
	}

}
