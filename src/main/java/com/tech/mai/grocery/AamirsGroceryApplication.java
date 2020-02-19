package com.tech.mai.grocery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class AamirsGroceryApplication implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(AamirsGroceryApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Application Started");

		SpringApplication.run(AamirsGroceryApplication.class, args);

		LOGGER.info("Application Ended");
	}

	@Override
	public void run(String... items) {

		if (items.length > 0) {
			System.out.println("Shopping Items:");
			Arrays.asList(items).forEach(System.out::println);
		}

	}
}
