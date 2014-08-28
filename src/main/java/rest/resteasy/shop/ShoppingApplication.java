package rest.resteasy.shop;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import rest.resteasy.shop.resource.bean.CustomerResourceBean;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resteasy/shop")
public class ShoppingApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public ShoppingApplication() {
		singletons.add(new CustomerResourceBean());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
