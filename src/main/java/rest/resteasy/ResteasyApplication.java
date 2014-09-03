package rest.resteasy;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import rest.resteasy.resource.CustomerResource;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

@ApplicationPath("/resteasy")
public class ResteasyApplication extends Application {

	/**
	 * 注册各个resource实现类
	 *  @see ServiceLoader
	 */
	private CustomerResource customerResource = ServiceLoader.load(CustomerResource.class).iterator().next();

	private Set<Object> singletons = new HashSet<Object>();

	public ResteasyApplication() {
		singletons.add(customerResource);
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
