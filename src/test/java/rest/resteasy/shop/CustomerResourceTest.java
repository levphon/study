package rest.resteasy.shop;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import rest.resteasy.shop.domain.Customer;

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

}
