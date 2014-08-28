package spring.rest.client;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import rest.resteasy.shop.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/beans.xml")
public class RestfulClientTest {

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

}
