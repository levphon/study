package rest.resteasy;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import rest.resteasy.resource.bean.CustomerResourceBean;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resteasy")
public class ResteasyApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public ResteasyApplication() {
		singletons.add(new CustomerResourceBean());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}