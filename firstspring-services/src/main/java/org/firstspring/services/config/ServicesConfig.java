package org.firstspring.services.config;

import org.firstspring.model.config.ModelConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Class for Annotation Type Configuration
 * @author Giuseppe Carrassa
 *
 */

@Configuration
@Import(ModelConfig.class)
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = { "org.firstspring.services" })
@Profile("production")
public class ServicesConfig {

}
