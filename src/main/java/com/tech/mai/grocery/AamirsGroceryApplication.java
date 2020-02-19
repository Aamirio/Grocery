package com.tech.mai.grocery;

import com.tech.mai.grocery.service.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.Period.ofDays;

@SpringBootApplication
public class AamirsGroceryApplication implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(AamirsGroceryApplication.class);

	@Autowired
	public BasketService basketService;

	public static void main(String[] args) {
		LOGGER.info("Application Started");

		SpringApplication.run(AamirsGroceryApplication.class, args);

		LOGGER.info("Application Ended");
	}

	@Override
	public void run(String... items) {

		if (items.length > 1) {
			final List<String> inputList = new ArrayList<>(Arrays.asList(items));
			final String daysOffset = inputList.remove(0);
			final LocalDate purchaseDate = LocalDate.now().plus(ofDays(Integer.valueOf(daysOffset)));

			System.out.println(basketService.createBasket(inputList, purchaseDate));
		}

	}
}
