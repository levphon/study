package restful.resteasy.shop.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import restful.resteasy.shop.domain.Customer;

@Path("/customers")
public interface CustomerResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract Response createCustomer(Customer customer);

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

}
