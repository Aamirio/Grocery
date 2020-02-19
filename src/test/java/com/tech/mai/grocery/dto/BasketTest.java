package com.tech.mai.grocery.dto;

import com.tech.mai.grocery.domain.StockItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import static com.tech.mai.grocery.fixture.TestFixtures.APPLE;
import static com.tech.mai.grocery.fixture.TestFixtures.BREAD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class BasketTest {

    @Test
    public void shouldAddStockItemsAndUpdateQuantity_whenAddingStockItemsOneAtATime() {

        final Basket basket = new Basket(LocalDate.now());

        basket.addStockItem(APPLE);
        basket.addStockItem(BREAD);
        basket.addStockItem(APPLE);

        final Map<StockItem, Integer> stockItems = basket.getStockItems();

        assertThat(stockItems.size()).isEqualTo(2);

        stockItems.entrySet().forEach( entry -> {

            switch (entry.getKey().getName()) {
                case "apple" : assertThat(entry.getValue()).isEqualTo(2); break;
                case "bread" : assertThat(entry.getValue()).isEqualTo(1); break;
                default: fail(String.format("Unexpected item in basket: %s", entry.getKey().getName()));
            }
        });
    }

    @Test
    public void shouldAddStockItemsAndUpdateQuantity_whenAddingMultipleStockItems() {

        final Basket basket = new Basket(LocalDate.now());

        basket.addStockItems(Arrays.asList(APPLE, BREAD, APPLE));

        final Map<StockItem, Integer> stockItems = basket.getStockItems();

        assertThat(stockItems.size()).isEqualTo(2);

        stockItems.entrySet().forEach( entry -> {

            switch (entry.getKey().getName()) {
                case "apple" : assertThat(entry.getValue()).isEqualTo(2); break;
                case "bread" : assertThat(entry.getValue()).isEqualTo(1); break;
                default: fail(String.format("Unexpected item in basket: %s", entry.getKey().getName()));
            }
        });
    }

    @Test
    public void toString_shouldReturnBasketItemsAndTotalAsString() {

        final Basket basket = new Basket(LocalDate.now());

        basket.addStockItem(APPLE);
        basket.addStockItem(BREAD);
        basket.addStockItem(APPLE);

        basket.setTotal(new BigDecimal("0.98"));

        assertThat(basket.toString())
                .containsOnlyOnce("1 x bread")
                .containsOnlyOnce("2 x apple")
                .containsOnlyOnce("Total cost: £0.98");
    }
}