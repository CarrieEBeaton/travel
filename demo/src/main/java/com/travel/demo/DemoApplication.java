package com.travel.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.travel.demo.controller.UserController;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController controller = 
           ctx.getBean("controller", UserController.class);
		    
		SpringApplication.run(DemoApplication.class, args);
	}
}

//Create an API from scratch that takes user names, emails, one country they could
//attend and what dates they could attend that country. Find which dates for each country
//that the most people could attend in two consecutive days and return the dates as strings
//that they would be attending them. POST the countries with the dates as JSON properties
//to their server until you get a status of 200. The resulting JSON they're
//looking for does not match the example result provided.


