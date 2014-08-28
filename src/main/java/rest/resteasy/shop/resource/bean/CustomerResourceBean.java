package rest.resteasy.shop.resource.bean;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.core.Response;

import rest.resteasy.shop.domain.Customer;
import rest.resteasy.shop.resource.CustomerResource;

public class CustomerResourceBean implements CustomerResource {

	private AtomicInteger idCounter = new AtomicInteger();
	private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();

	@Override
	public Response createCustomer(Customer customer) {
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		return Response.created(URI.create("/customers/" + customer.getId())).build();
	}

	@Override
	public Collection<Customer> getCustomers() {
		return customerDB.values();
	}

	@Override
	public Customer getCustomer(Integer id) {
		return customerDB.get(id);
	}

	@Override
	public Customer getCustomer(String firstName, String lastName) {
		Customer customer = null;
		for (Map.Entry<Integer, Customer> entry : customerDB.entrySet()) {
			Customer value = entry.getValue();
			if (value.getFirstName().equals(firstName) && value.getLastName().equals(lastName)) {
				customer = value;
				break;
			}
		}
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDB.put(customer.getId(), customer);
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerDB.remove(id);
	}

	private Map<Integer, rest.resteasy.shop.domain.xml.Customer> customerDB_xml = new ConcurrentHashMap<Integer, rest.resteasy.shop.domain.xml.Customer>();

	@Override
	public Response createCustomer_xml(rest.resteasy.shop.domain.xml.Customer customer) {
		customer.setId(idCounter.incrementAndGet());
		customerDB_xml.put(customer.getId(), customer);
		return Response.created(URI.create("/customers/xml/" + customer.getId())).build();
	}

	@Override
	public Collection<rest.resteasy.shop.domain.xml.Customer> getCustomers_xml() {
		return customerDB_xml.values();
	}

	@Override
	public rest.resteasy.shop.domain.xml.Customer getCustomer_xml(Integer id) {
		return customerDB_xml.get(id);
	}

}
