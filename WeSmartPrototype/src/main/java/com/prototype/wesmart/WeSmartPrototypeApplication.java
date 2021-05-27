package com.prototype.wesmart;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WeSmartPrototype Spring Boot Application.
 *
 * @author Andres Calderon - andres.calderon@payu.com
 * @version 0.0.1
 * @since 0.0.1
 */
@Log4j2
@SpringBootApplication
public class WeSmartPrototypeApplication {

	/**
	 * Main method to start service.
	 *
	 * @param args service arguments.
	 */
	public static void main(String[] args) {

		SpringApplication.run(WeSmartPrototypeApplication.class, args);
		log.info("Application Starting ...");
	}

}
