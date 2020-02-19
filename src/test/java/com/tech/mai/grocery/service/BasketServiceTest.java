package com.tech.mai.grocery.service;

import com.tech.mai.grocery.dto.Basket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class BasketServiceTest {

    @Autowired
    BasketService basketService;

    @Test
    public void shouldCreateBasket_whenAddingValidItems() {

        final Basket basket = basketService.createBasket(Arrays.asList("Apple", "Soup", "Milk", "apple"), LocalDate.now());

        assertThat(basket.getStockItems().entrySet().size()).isEqualTo(3);
        assertItemsAndQuantitiesInBasket(basket, 1, 0, 1, 2);
    }

    @Test
    public void shouldCreateBasket_withTotalCost_whenAddingValidItems() {

        final Basket basket = basketService.createBasket(Arrays.asList("Apple", "Milk", "Apple"), LocalDate.now());

        assertThat(basket.getTotal()).isEqualTo(new BigDecimal("1.50"));
        assertThat(basket.getStockItems().entrySet().size()).isEqualTo(2);
        assertItemsAndQuantitiesInBasket(basket, 0, 0, 1, 2);
    }

    @Test
    public void shouldUpdateBasket_whenAddingValidItemToExistingBasket() {

        Basket basket = basketService.addItemToBasket(new Basket(LocalDate.now()), "apple");

        assertItemsAndQuantitiesInBasket(basket, 0, 0, 0, 1);

        basket = basketService.addItemToBasket(basket, "apple");

        assertItemsAndQuantitiesInBasket(basket, 0, 0, 0, 2);
    }

    private void assertItemsAndQuantitiesInBasket(Basket basket, int noOfSoupTins, int noOfBreadLoaves, int noOfMilkBottles, int noOfApples) {
        basket.getStockItems().entrySet().forEach(entry -> {

            switch (entry.getKey().getName()) {
                case "soup":  assertThat(entry.getValue()).isEqualTo(noOfSoupTins); break;
                case "bread": assertThat(entry.getValue()).isEqualTo(noOfBreadLoaves); break;
                case "milk":  assertThat(entry.getValue()).isEqualTo(noOfMilkBottles); break;
                case "apple": assertThat(entry.getValue()).isEqualTo(noOfApples); break;

                default: fail(String.format("%s is an unexpected stock item", entry.getKey().getName()));
            }
        });
    }
}