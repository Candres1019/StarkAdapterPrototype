package com.wesmart.stark;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@Log4j2
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class StarkApplication {

	public static void main(String[] args) {

		SpringApplication.run(StarkApplication.class, args);
		log.info("Application Starting ...");
	}

}
