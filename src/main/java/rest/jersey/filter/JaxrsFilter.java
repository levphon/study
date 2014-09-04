package rest.jersey.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class JaxrsFilter implements ContainerRequestFilter,
		ContainerResponseFilter, ClientRequestFilter, ClientResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {

	}

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

	}

	@Override
	public void filter(ClientRequestContext requestContext,
			ClientResponseContext responseContext) throws IOException {

	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {

	}

}
