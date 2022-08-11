package com.zee.zee5app;

import java.time.LocalDate;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repo.UserRepo;
import com.zee.zee5app.service.UserService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// creates object for repo, service, dbutils (frequently required)
		ApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(Config.class);
		
		UserService userService = applicationContext.getBean(UserService.class);
		//System.out.println(userService != null);
		
		//UserRepo userRepo = applicationContext.getBean(UserRepo.class);
		/*UserRepo userRepo1 = applicationContext.getBean(UserRepo.class);
		System.out.println(userRepo.hashCode() == userRepo1.hashCode());
		//System.out.println(userRepo != null);*/
		
		/*Properties properties = applicationContext.getBean("properties", Properties.class);
		System.out.println(properties);
		Properties properties2 = applicationContext.getBean("properties", Properties.class);
		System.out.println(properties == properties2);*/
		
		try {
			User user = userService.insertUser(new User("nikhil", "naik", "rnikhil5514@gmail.com", LocalDate.now(), LocalDate.of(2001, 5, 26)));
			
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
