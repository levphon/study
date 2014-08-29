package spring.rest.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public final class RequestEntity {

	private static final HttpHeaders jsonHeaders;

	private static final HttpHeaders xmlHeaders;

	private RequestEntity() {
	}

	static {
		jsonHeaders = new HttpHeaders();
		jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
		xmlHeaders = new HttpHeaders();
		xmlHeaders.setContentType(MediaType.APPLICATION_XML);
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
