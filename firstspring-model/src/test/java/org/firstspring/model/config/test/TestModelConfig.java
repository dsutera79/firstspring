package org.firstspring.model.config.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Class for Annotation Type Configuration
 * @author Giuseppe Carrassa
 *
 */
@Configuration
@ComponentScan(basePackages = { "org.firstspring.model"})
@Profile("test")
public class TestModelConfig {
	@Bean
	public EmbeddedDatabase dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("classpath:sql/test/db_Activity_init.sql")
			.addScript("classpath:sql/test/db_Activity_data_init.sql")
			.build();
		return db;
	}
}
