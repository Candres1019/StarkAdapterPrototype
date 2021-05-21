package com.wesmart.stark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class StarkApplication {

	public static void main(String[] args) {

		SpringApplication.run(StarkApplication.class, args);
	}

}
