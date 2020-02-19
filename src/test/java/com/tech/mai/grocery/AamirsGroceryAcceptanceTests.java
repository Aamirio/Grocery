package com.tech.mai.grocery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AamirsGroceryAcceptanceTests {

	private final ByteArrayOutputStream output = new ByteArrayOutputStream();

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(output));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(System.out);
	}

	@Autowired
	private AamirsGroceryApplication groceryApp;

	@Test
	public void shouldPrintTotal_whenBuyingSixApplesAndOneMilk_today() {
		groceryApp.run("0", "Apple", "Milk", "Apple", "Apple", "Apple", "Apple", "Apple");

		assertThat(output.toString())
				.containsOnlyOnce("6 x apple")
				.containsOnlyOnce("1 x milk")
				.containsOnlyOnce("Total cost: £1.90");
	}

}
