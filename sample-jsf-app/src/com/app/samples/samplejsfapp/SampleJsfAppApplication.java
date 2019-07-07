package com.app.samples.samplejsfapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// TODO: Auto-generated Javadoc
/**
 * The Class SampleJsfAppApplication.
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.app.samples.samplejsfapp")
@EnableConfigurationProperties
public class SampleJsfAppApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SampleJsfAppApplication.class, args);
	}

}
