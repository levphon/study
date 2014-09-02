package spring.rest.client;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import rest.domain.Customer;
import rest.domain.Customer_xml;

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

		// beans.xml将MappingJackson2HttpMessageConverter放最后,或者RequestEntity(手动设置HttpHeaders)
		URI location = this.restTemplate.postForLocation(uri_xml, customer);
//		URI location = this.restTemplate.postForLocation(uri_xml, RequestEntity.xml(customer));
		System.out.println("location:" + location);
		Customer_xml customerGet = this.restTemplate.getForObject(location, Customer_xml.class);
		System.out.println(customerGet);
	}

	@Test
	public void testGetCustomers_xml() {
		ParameterizedTypeReference<List<Customer_xml>> typeReference = new ParameterizedTypeReference<List<Customer_xml>>() {};
		ResponseEntity<List<Customer_xml>> responseEntity = this.restTemplate.exchange(URI.create(uri_xml), HttpMethod.GET, null, typeReference);
		System.out.println(responseEntity.getBody());
	}

}
