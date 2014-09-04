package rest.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
public interface CustomerResource {

	@GET
	@Path("/text")
	@Produces(MediaType.TEXT_PLAIN)
	public abstract String hello();

}
