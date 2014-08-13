package spring.javaconfig;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebApplicationInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "dispatcherServlet";
	private static final String LOCATION = null;
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 1024;
	private static final long MAX_REQUEST_SIZE = -1L;
	private static final int FILE_SIZE_THRESHOLD = 1024 * 1024;

	private static final String ENCODING = "utf-8";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		this.registerContextLoaderListener(servletContext);
		this.registerDispatcherServlet(servletContext);
		this.registerCharacterEncodingFilter(servletContext);
	}

	private WebApplicationContext createWebApplicationContext(Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(annotatedClasses);
		return context;
	}

	private void registerContextLoaderListener(ServletContext servletContext) {
		WebApplicationContext webApplicationContext = this.createWebApplicationContext(ApplicationContextConfiguration.class);
		ContextLoaderListener listener = new ContextLoaderListener(webApplicationContext);
		servletContext.addListener(listener);
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		WebApplicationContext webApplicationContext = this.createWebApplicationContext(WebMvcContextConfiguration.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");

		servlet.setMultipartConfig(new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
	}

	private void registerCharacterEncodingFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(ENCODING);
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
		filter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, DISPATCHER_SERVLET_NAME);
	}

}
