package rest.resteasy;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import rest.domain.Customer;
import rest.domain.Customer_xml;

public class CustomerResourceTest {

	private Client client;

	@Before
	public void before() {
		client = ClientBuilder.newClient();
	}

	@After
	public void after() {
		client.close();
	}

	private static String uri = "http://localhost:8080/study/resteasy/customers";

	@Test
	public void testCreateCustomerAndGetCustomer() {
		Customer customer = new Customer();
		Customer getCustomer = client.target(uri).request(MediaType.APPLICATION_JSON_VALUE).post(Entity.json(customer), Customer.class);
		System.out.println(getCustomer);
	}

	@Test
	public void testCreateCustomersAndGetCustomers() {
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		List<Customer> customers = Arrays.asList(customer1, customer2);
		List<Customer> allCustomers = client.target(uri + "/jsons").request(MediaType.APPLICATION_JSON_VALUE).post(Entity.json(customers), new GenericType<List<Customer>>() {});
		System.out.println(allCustomers);
	}

	@Test
	public void testGetCustomers() {
		List<Customer> customers = client.target(uri).request(MediaType.APPLICATION_JSON_VALUE).get(new GenericType<List<Customer>>() {});
		System.out.println(customers);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setLastName("mumu");
		Response response = ClientBuilder.newClient().target(uri).request(MediaType.APPLICATION_JSON_VALUE).put(Entity.json(customer));
		System.out.println(response.getStatus());
		response.close();
	}

	@Test
	public void testDeleteCustomer() {
		Response response = ClientBuilder.newClient().target(uri + "/1").request(MediaType.APPLICATION_JSON_VALUE).delete();
		System.out.println(response.getStatus());
		response.close();
	}

	private static String uri_xml = "http://localhost:8080/study/resteasy/customers/xml";

	@Test
	public void testCreateCustomer_xmlAndGetCustomer_xml() {
		Customer_xml customer = new Customer_xml();
		Customer_xml getCustomer = client.target(uri_xml).request(MediaType.APPLICATION_XML_VALUE).post(Entity.xml(customer), Customer_xml.class);
		System.out.println(getCustomer);
	}

	@Test
	public void testCreateCustomers_xmlAndGetCustomers_xml() {
		Customer_xml customer1 = new Customer_xml();
		Customer_xml customer2 = new Customer_xml();
		List<Customer_xml> customers = Arrays.asList(customer1, customer2);
		List<Customer_xml> getCustomers = client.target(uri_xml + "/xmls").request(MediaType.APPLICATION_XML_VALUE).post(Entity.xml(customers), new GenericType<List<Customer_xml>>() {});
		System.out.println(getCustomers);
	}

	@Test
	public void testGetCustomers_xml() {
		List<Customer_xml> customers = client.target(uri_xml).request(MediaType.APPLICATION_XML_VALUE).get(new GenericType<List<Customer_xml>>() {});
		System.out.println(customers);
	}

}
