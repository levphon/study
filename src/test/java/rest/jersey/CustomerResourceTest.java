package rest.jersey;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import rest.jersey.resource.bean.CustomerResourceBean;

public class CustomerResourceTest {

	/**
	 * http://localhost:8080/grizzly/jersey/customers/text
	 */
	public static void main(String[] args) throws IOException {
		URI BASE_URI = URI.create("http://localhost:8080/grizzly/jersey");
		ResourceConfig configuration = new ResourceConfig(CustomerResourceBean.class);
		HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, configuration);
		System.in.read();	// Enter to stop!
		httpServer.shutdownNow();
	}

}
