package com.tech.mai.grocery;

import com.tech.mai.grocery.service.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tech.mai.grocery.Constants.ERROR_FIRST_ARG;
import static com.tech.mai.grocery.Constants.ERROR_MIN_TWO_ARGS;

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
	public void run(String... args) {



		if (args.length < 2) { System.err.println(ERROR_MIN_TWO_ARGS); }
		else {
			final List<String> inputList = new ArrayList<>(Arrays.asList(args));
			final String daysOffset = inputList.remove(0);

			try { Integer.parseInt(daysOffset); }
			catch (NumberFormatException nfe) {
				System.err.println(ERROR_FIRST_ARG);
				return;
			}

			final LocalDate purchaseDate = LocalDate.now().plus(Period.ofDays(Integer.valueOf(daysOffset)));

			System.out.println(basketService.createBasket(inputList, purchaseDate));
		}

	}
}
