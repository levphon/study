package rest.resteasy.resource.bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import rest.domain.Customer;
import rest.domain.Customer_xml;
import rest.resteasy.resource.ResteasyResource;

public class RestsasyResourceBean implements ResteasyResource {

	private AtomicInteger idCounter = new AtomicInteger();
	private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();

	@Override
	public Customer createCustomer(Customer customer) {
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		return customer;
//		return Response.created(URI.create("/customers/" + customer.getId())).build();
	}

	@Override
	public Collection<Customer> createCustomers(List<Customer> customers) {
		for(Customer customer : customers) {
			customer.setId(idCounter.incrementAndGet());
			customerDB.put(customer.getId(), customer);
		}
		return customerDB.values();
//		return Response.created(URI.create("/customers")).build();
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

	private Map<Integer, Customer_xml> customerDB_xml = new ConcurrentHashMap<Integer, Customer_xml>();

	@Override
	public Customer_xml createCustomer_xml(Customer_xml customer) {
		customer.setId(idCounter.incrementAndGet());
		customerDB_xml.put(customer.getId(), customer);
		return customer;
//		return Response.created(URI.create("/customers/xml/" + customer.getId())).build();
	}

	@Override
	public Collection<Customer_xml> createCustomers_xml(List<Customer_xml> customers) {
		for(Customer_xml customer : customers) {
			customer.setId(idCounter.incrementAndGet());
			customerDB_xml.put(customer.getId(), customer);
		}
		return customerDB_xml.values();
	}

	@Override
	public Collection<Customer_xml> getCustomers_xml() {
		return customerDB_xml.values();
	}

	@Override
	public Customer_xml getCustomer_xml(Integer id) {
		return customerDB_xml.get(id);
	}

}
