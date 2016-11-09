package org.firstspring.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Class for Annotation Type Configuration
 * 
 * @author Giuseppe Carrassa
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
@PropertySource("classpath:general.properties")
@ComponentScan(basePackages = { "org.firstspring.web", "org.firstspring.services", "org.firstspring.model" })
public class WebConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		
		String dburl = env.getProperty("db.jdbcurl");
		String dbdriver = env.getRequiredProperty("db.driver");
		String dbusername = env.getRequiredProperty("db.username");
		String dbpassword = env.getRequiredProperty("db.password");
		
		DriverManagerDataSource dataSource = 
				new DriverManagerDataSource(dburl, 
						dbusername,dbpassword);
		
		dataSource.setDriverClassName(dbdriver);
		return dataSource;
	}
}

