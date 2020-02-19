package com.tech.mai.grocery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.tech.mai.grocery.Constants.ERROR_FIRST_ARG;
import static com.tech.mai.grocery.Constants.ERROR_MIN_TWO_ARGS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AamirsGroceryAcceptanceTests {

	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(output));
		System.setErr(new PrintStream(errorOutput));
	}

	@Autowired
	private AamirsGroceryApplication groceryApp;

	@Test
	public void shouldPrintError_whenLessThanTwoArgumentsProvided() {
		groceryApp.run("0");
		assertThat(errorOutput.toString()).containsOnlyOnce(ERROR_MIN_TWO_ARGS);
	}

	@Test
	public void shouldPrintError_whenNoPurchaseDayProvided() {
		groceryApp.run("Soup", "Bread");

		assertThat(errorOutput.toString()).containsOnlyOnce(ERROR_FIRST_ARG);
		assertThat(output.toString()).doesNotContain("Total cost:");
	}

	@Test
	public void shouldPrintTotal_whenBuyingSixApplesAndOneMilk_today() {
		groceryApp.run("0", "Apple", "Milk", "Apple", "Apple", "Apple", "Apple", "Apple");

		assertThat(output.toString())
				.containsOnlyOnce("6 x apple")
				.containsOnlyOnce("1 x milk")
				.containsOnlyOnce("Total cost: £1.90");
	}

	@Test
	public void shouldPrintTotal_whenBuyingSixApplesAndOneMilk_fiveDaysTime() {
		groceryApp.run("5", "Apple", "Milk", "Apple", "Apple", "Apple", "Apple", "Apple");

		assertThat(output.toString())
				.containsOnlyOnce("6 x apple")
				.containsOnlyOnce("1 x milk")
				.containsOnlyOnce("Total cost: £1.84");
	}

	@Test
	public void shouldPrintTotal_whenBuyingThreeSoupAndTwoBread_today() {
		groceryApp.run("0", "Soup", "soup", "Bread", "Soup", "Bread");

		assertThat(output.toString())
				.containsOnlyOnce("2 x bread")
				.containsOnlyOnce("3 x soup")
				.containsOnlyOnce("Total cost: £3.15");
	}

	@Test
	public void shouldPrintTotal_whenBuyingThreeApplesTwoSoupAndOneBread_fiveDaysTime() {
		groceryApp.run("5", "Soup", "Apple", "Soup", "Apple", "Apple", "Bread");

		assertThat(output.toString())
				.containsOnlyOnce("1 x bread")
				.containsOnlyOnce("2 x soup")
				.containsOnlyOnce("3 x apple")
				.containsOnlyOnce("Total cost: £1.97");
	}

}
