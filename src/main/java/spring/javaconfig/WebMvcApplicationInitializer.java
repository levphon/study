package spring.javaconfig;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@MultipartConfig
public class WebMvcApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationContextConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcContextConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	@Override
	protected void customizeRegistration(javax.servlet.ServletRegistration.Dynamic registration) {
		MultipartConfig multipartConfig = this.getClass().getAnnotation(MultipartConfig.class);
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(multipartConfig);
		registration.setMultipartConfig(multipartConfigElement);
	}

}
