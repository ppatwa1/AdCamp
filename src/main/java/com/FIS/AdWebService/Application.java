/*
 * @Author Prachi Patwa
 * 
 *Source of definition for Application
 *Tells Spring to look for Components and Services in com.FIA.AdWebService package
 * 
 */
package com.FIS.AdWebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

