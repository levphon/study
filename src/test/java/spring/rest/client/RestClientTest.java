package spring.rest.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import rest.resteasy.shop.domain.Customer;
import rest.resteasy.shop.domain.Customer_xml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class RestClientTest {

	private static String uri = "http://localhost:8080/study/resteasy/shop/customers";

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testCreateAndGetCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Wang");
		customer.setLastName("mumu");

		URI location = this.restTemplate.postForLocation(uri, customer);
		System.out.println("location:" + location);
		Customer customerGet = this.restTemplate.getForObject(location, Customer.class);
		System.out.println(customerGet);
	}

	@Test
	public void testGetCustomers() {
		List<?> customers = this.restTemplate.getForObject(uri, List.class);
		System.out.println(customers);
	}

	@Test
	public void testGetCustomer() {
		Customer customer = this.restTemplate.getForObject(uri + "/1", Customer.class);
		System.out.println(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setLastName("mumumu");

		this.restTemplate.put(uri, customer);
	}

	@Test
	public void testDeleteCustomer() {
		this.restTemplate.delete(uri + "/1");
	}

	private static String uri_xml = "http://localhost:8080/study/resteasy/shop/customers/xml";

	@Test
	public void testCreateAndGetCustomer_xml() {
		Customer_xml customer = new Customer_xml();
		customer.setFirstName("Wang");
		customer.setLastName("mumu");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", MediaType.APPLICATION_XML);
		HttpEntity<Customer_xml> httpEntity = new HttpEntity<Customer_xml>(customer, httpHeaders);
		URI location = this.restTemplate.postForLocation(uri_xml, httpEntity);
		System.out.println("location:" + location);
		Customer_xml customerGet = this.restTemplate.getForObject(location, Customer_xml.class);
		System.out.println(customerGet);
	}

	@Test
	public void testGetCustomers_xml() {
		List<?> customers = this.restTemplate.getForObject(uri_xml, List.class);
		System.out.println(customers);
	}

}
