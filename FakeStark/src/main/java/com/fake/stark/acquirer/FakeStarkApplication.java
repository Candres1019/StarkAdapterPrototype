package com.fake.stark.acquirer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FakeStark Spring Boot Application.
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootApplication
public class FakeStarkApplication {

	/**
	 * Main method to start service.
	 *
	 * @param args service arguments.
	 */
	public static void main(String[] args) {

		SpringApplication.run(FakeStarkApplication.class, args);
	}

}
