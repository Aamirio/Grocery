package com.tech.mai.grocery.service;

import com.tech.mai.grocery.dto.Basket;
import com.tech.mai.grocery.repository.CatalogueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.tech.mai.grocery.fixture.TestFixtures.ALL_STOCK_ITEMS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BasketServiceTest {

    @InjectMocks
    BasketService basketService;

    @Mock
    CatalogueRepository catalogueRepository;

    @Mock
    PricingService pricingService;

    @BeforeEach
    private void setup() {
        when(catalogueRepository.getAllStockItems()).thenReturn(ALL_STOCK_ITEMS);
        when(pricingService.calculateTotalCost(any(), any())).thenReturn(new BigDecimal("1.50"));
    }

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

    @Test
    public void shouldNotUpdateBasket_whenAddingInvalidItemToExistingBasket() {

        Basket basket = basketService.addItemToBasket(new Basket(LocalDate.now()), "pear");

        assertItemsAndQuantitiesInBasket(basket, 0, 0, 0, 0);

    }

    @Test
    public void shouldCreateBasket_withOnlyValidItems_whenAddingValidAndInvalidItems() {

        final Basket basket = basketService.createBasket(Arrays.asList("Apple", "Soup", "Milk", "apple", "banana"), LocalDate.now());

        assertThat(basket.getStockItems().entrySet().size()).isEqualTo(3);
        assertItemsAndQuantitiesInBasket(basket, 1, 0, 1, 2);
    }

    @Test
    public void shouldCreateEmptyBasket_whenAddingOnlyInvalidItems() {

        assertThat(basketService.createBasket(Arrays.asList("banana", "pear"), LocalDate.now()).getStockItems().size()).isEqualTo(0);
    }

    @Test
    public void shouldCreateEmptyBasket_whenAddingNoItems() {

        assertThat(basketService.createBasket(new ArrayList<>(), LocalDate.now()).getStockItems().size()).isEqualTo(0);
        assertThat(basketService.createBasket(null, LocalDate.now()).getStockItems().size()).isEqualTo(0);
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