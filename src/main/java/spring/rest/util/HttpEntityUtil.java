package spring.rest.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import rest.resteasy.shop.domain.Customer;

public final class HttpEntityUtil {

	private static final HttpHeaders jsonHeaders;

	private static final HttpHeaders xmlHeaders;

	private HttpEntityUtil() {
	}

	static {
		jsonHeaders = new HttpHeaders();
		jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
		xmlHeaders = new HttpHeaders();
		xmlHeaders.setContentType(MediaType.APPLICATION_XML);
	}

	public static void main(String[] args) {
		Customer customer = new Customer();
		HttpEntityUtil.json(customer);
	}

	public static HttpEntity<Object> json(Object request) {
		return new HttpEntity<Object>(request, jsonHeaders);
	}

	public static <T> HttpEntity<T> json(T request, Class<T> clazz) {
		return new HttpEntity<T>(request, jsonHeaders);
	}

	public static HttpEntity<Object> xml(Object request) {
		return new HttpEntity<Object>(request, xmlHeaders);
	}

	public static <T> HttpEntity<T> xml(T request, Class<T> clazz) {
		return new HttpEntity<T>(request, xmlHeaders);
	}

}
