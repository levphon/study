package rest.jersey.resource.bean;

import rest.jersey.resource.CustomerResource;

public class CustomerResourceBean implements CustomerResource {

	@Override
	public String hello() {
		return "hello world!";
	}

}
