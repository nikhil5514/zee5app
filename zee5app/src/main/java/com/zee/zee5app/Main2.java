package com.zee.zee5app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.config.ConfigTwo;

public class Main2 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(Config.class,ConfigTwo.class);
		
		DataSource dataSource = applicationContext.getBean("dataSourceTwo", DataSource.class);
		
		System.out.println(dataSource != null);
		
		
		
		//DataSource
		

	}

}
