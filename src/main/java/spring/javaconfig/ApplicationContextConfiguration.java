package spring.javaconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "spring" }, excludeFilters = { @Filter( Configuration.class ) })
//@ComponentScan(basePackages = { "spring" }, excludeFilters = { @Filter(type = FilterType.REGEX, pattern = "spring.javaconfig.*") })
@PropertySource("classpath:spring/jdbc/jdbc.properties")
public class ApplicationContextConfiguration implements TransactionManagementConfigurer {

	public static @Bean PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;

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

}
