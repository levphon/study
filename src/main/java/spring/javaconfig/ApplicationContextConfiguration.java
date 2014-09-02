package spring.javaconfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.client.RestTemplate;

import rest.domain.Customer_xml;

@Profile("java")
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "spring" }, excludeFilters = { @Filter( Configuration.class ) })
//@ComponentScan(basePackages = { "spring" }, excludeFilters = { @Filter(type = FilterType.REGEX, pattern = "spring.javaconfig.*") })
@PropertySource("classpath:spring/jdbc/jdbc.properties")
public class ApplicationContextConfiguration implements TransactionManagementConfigurer {

	@Autowired
	private Environment environment;

	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;

	public static @Bean PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public @Bean DataSource dataSource() {
		return new DriverManagerDataSource(url, username, password);
	}

	public @Bean JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Override
	public @Bean PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	public @Bean RestTemplate restTemplate() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MarshallingHttpMessageConverter(marshaller(), marshaller()));
		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
		messageConverters.add(new Jaxb2CollectionHttpMessageConverter<Collection<?>>());
		messageConverters.add(new MappingJackson2HttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	public @Bean Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Map<String, Object> marshallerProperties = new HashMap<String, Object>();
		marshallerProperties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setMarshallerProperties(marshallerProperties);
		marshaller.setClassesToBeBound(Customer_xml.class);
		return marshaller;
	}

}
