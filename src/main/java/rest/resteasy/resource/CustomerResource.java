package rest.resteasy.resource;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import rest.domain.Customer;
import rest.domain.Customer_xml;

@Path("/customers")
public interface CustomerResource {

	// json

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Customer createCustomer(Customer customer);

	@POST
	@Path("/jsons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Collection<Customer> createCustomers(List<Customer> customers);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Collection<Customer> getCustomers();

	@GET
	@Path("/{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Customer getCustomer(@PathParam("id") Integer id);

	@GET
	@Path("/{firstName}-{lastName}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Customer getCustomer(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract void updateCustomer(Customer customer);

	@DELETE
	@Path("/{id : \\d+}")
	public abstract void deleteCustomer(@PathParam("id") Integer id);


	// xml

	@POST
	@Path("/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public abstract Customer_xml createCustomer_xml(Customer_xml customer);

	@POST
	@Path("/xml/xmls")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public abstract Collection<Customer_xml> createCustomers_xml(List<Customer_xml> customers);

	@GET
	@Path("/xml")
	@Wrapped(element = "customers")
	@Produces(MediaType.APPLICATION_XML)
	public abstract Collection<Customer_xml> getCustomers_xml();

	@GET
	@Path("/xml/{id : \\d+}")
	@Produces(MediaType.APPLICATION_XML)
	public abstract Customer_xml getCustomer_xml(@PathParam("id") Integer id);

}
