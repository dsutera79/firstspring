package org.firstspring.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
// import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Class for Annotation Type Configuration
 * @author Giuseppe Carrassa
 *
 */

@Configuration


/** @PropertySources({	<-- più file di configurazione (con Java8 si può ignorare
* @PropertySource(value="classpath:missing.properties", ignoreResourceNotFound=true)	<-- ignore le conf non trovate
* @PropertySource("classpath:config.properties"),
* @PropertySource("classpath:db.properties") // <--se ci sono conflitti tra config prevale l'ultimo
* })
*	Alternativa per più file di configurazione:
* @PropertySource({"default.properties", "overriding.properties"})
*/


@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = { "org.firstspring.model" })
			//({"package1","package2",...},basePackageClass=Classe.class)
			//dalla classe deriva il package
			//excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ClasseDaEscludere.class))
			//includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ClasseDaIncludereclass),useDefaultFilters = false)

@Profile("production")


public class ModelConfig {
	
	@Autowired	//<-- Inietta la Classe Environment
	private Environment env;  //env diventa un oggetto globale che non necessita di essere istanziato
	
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
	
	
/**
 * dal file di configurazione posso prelevare i valori tramite:
 * 
 * @Value("${radice.proprietà}")
 * private String variabile_proprietà;
 * 
 * Bisogna inserire questo bean per risolvere ${} in @Value:
 * 
 *	@Bean
 *	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
 *		return new PropertySourcesPlaceholderConfigurer();
 *
 *!!!(consigliato comunque l'uso della classe Environment)!!!
 */
	
	
}
