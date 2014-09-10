package spring;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rest.domain.Customer;
import rest.domain.Customer_xml;

@ActiveProfiles("xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/beans.xml" })
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

	@Test
	public void testJaxbMarshaller() throws XmlMappingException, IOException {
		Jaxb2Marshaller marshaller = context.getBean("jaxbMarshaller", Jaxb2Marshaller.class);
		marshaller.marshal(new Customer_xml(), new StreamResult(System.out));
	}

	@Test
	public void testXStreamMarshaller() throws XmlMappingException, IOException {
		XStreamMarshaller marshaller = context.getBean("xstreamMarshaller", XStreamMarshaller.class);
		marshaller.marshalOutputStream(new Customer(), System.out);
	}

}
