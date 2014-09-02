package rest.resteasy;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import rest.domain.Customer;
import rest.domain.Customer_xml;

public class CustomerResourceTest {

	private static String uri = "http://localhost:8080/study/resteasy/shop/customers";

	@Test
	public void testCreateAndGetCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Wang");
		customer.setLastName("mumu");
		Response postResponse = ClientBuilder.newClient().target(uri).request().post(Entity.json(customer));
		System.out.println(postResponse.getStatus());
		postResponse.close();

		URI getUri = postResponse.getLocation();
		Response getResponse = ClientBuilder.newClient().target(getUri).request().get();
		getResponse.bufferEntity();
		Customer getCustomer = getResponse.readEntity(Customer.class);
		System.out.println(getCustomer);
		getResponse.close();
	}

	@Test
	public void testGetCustomers() {
		Response response = ClientBuilder.newClient().target(uri).request().get();
		response.bufferEntity();
		List<Customer> customers = response.readEntity(new GenericType<List<Customer>>() {});
		System.out.println(customers);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setLastName("mumumu");
		Response response = ClientBuilder.newClient().target(uri).request().put(Entity.json(customer));
		System.out.println(response.getStatus());
		response.close();
	}

	@Test
	public void testDeleteCustomer() {
		Response response = ClientBuilder.newClient().target(uri + "/1").request().delete();
		System.out.println(response.getStatus());
		response.close();
	}

	private static String uri_xml = "http://localhost:8080/study/resteasy/shop/customers/xml";

	@Test
	public void testCreateAndGetCustomer_xml() {
		Customer_xml customer = new Customer_xml();
		customer.setFirstName("Wang");
		customer.setLastName("mumu");
		Response postResponse = ClientBuilder.newClient().target(uri_xml).request().post(Entity.xml(customer));
		System.out.println(postResponse.getStatus());
		postResponse.close();

		URI getUri = postResponse.getLocation();
		Response getResponse = ClientBuilder.newClient().target(getUri).request().get();
		getResponse.bufferEntity();
		Customer_xml getCustomer = getResponse.readEntity(Customer_xml.class);
		System.out.println(getCustomer);
		getResponse.close();
	}

	@Test
	public void testGetCustomers_xml() {
		Response response = ClientBuilder.newClient().target(uri_xml).request().get();
		response.bufferEntity();
		List<Customer_xml> customers = response.readEntity(new GenericType<List<Customer_xml>>() {});
		System.out.println(customers);
	}

}
