package com.pismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Pismo Spring Boot application.
 */
@SpringBootApplication
public class PismoApplication {

	/**
	 * Runs the Pismo application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PismoApplication.class, args);
	}
}
