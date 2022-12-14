package com.zee.zee5app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@Import(ConfigTwo.class)
@ComponentScan("com.zee.zee5app")
@PropertySource(value = "application.properties")

public class Config {
	
	@Autowired
	Environment environment;
	
	// data source ---> get the connection object
	@Bean("dataSource")
	public DataSource getDataSource() {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setUsername(environment.getProperty("db.username"));
		basicDataSource.setUrl(environment.getProperty("db.url"));
		basicDataSource.setPassword(environment.getProperty("db.password"));
		
		return basicDataSource;
	
	}
	
	/*@Bean(name = "properties")
	//@Scope("prototype")
	@Scope("singleton")
	public Properties getProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("username", environment.getProperty("db.username"));
		properties.setProperty("password", environment.getProperty("db.password"));
		
		return properties;
	}*/
}
