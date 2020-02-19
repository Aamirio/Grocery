package com.tech.mai.grocery.service;

import com.tech.mai.grocery.domain.StockItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import static com.tech.mai.grocery.fixture.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PricingServiceTest {

    @Autowired
    PricingService pricingService;

    @Test
    public void shouldCalculateTotalCost_onItemsNotDuringDiscountPeriod() {

        final Map<StockItem, Integer> stockItemsByQuantity = new HashMap<>();
        stockItemsByQuantity.put(APPLE, 2);
        stockItemsByQuantity.put(MILK, 1);

        final BigDecimal totalCost = pricingService.calculateTotalCost(stockItemsByQuantity, LocalDate.now());

        assertThat(totalCost).isEqualTo(new BigDecimal("1.50"));
    }

    @Test
    public void shouldCalculateTotalCost_onItemsDuringDiscountPeriod() {

        final Map<StockItem, Integer> stockItemsByQuantity = new HashMap<>();
        stockItemsByQuantity.put(APPLE, 2);

        final BigDecimal totalCost = pricingService.calculateTotalCost(stockItemsByQuantity, LocalDate.now().plus(Period.ofDays(3)));

        assertThat(totalCost).isEqualTo(new BigDecimal("0.18"));
    }

    @Test
    public void shouldCalculateTotalCost_onItemsOnDiscountDependentOnOtherStockItems() {

        final Map<StockItem, Integer> stockItemsByQuantity = new HashMap<>();
        stockItemsByQuantity.put(SOUP, 5);
        stockItemsByQuantity.put(BREAD, 3);

        assertThat(pricingService.calculateTotalCost(stockItemsByQuantity, LocalDate.now())).isEqualTo(new BigDecimal("4.85"));
    }

    @Test
    public void shouldCalculateTotalCost_onVariousItemsWithDifferentDiscountsDuringDiscountPeriod() {

        final Map<StockItem, Integer> stockItemsByQuantity = new HashMap<>();
        stockItemsByQuantity.put(BREAD, 1);
        stockItemsByQuantity.put(SOUP, 2);
        stockItemsByQuantity.put(APPLE, 3);

        assertThat(pricingService.calculateTotalCost(stockItemsByQuantity, LocalDate.now().plus(Period.ofDays(5)))).isEqualTo(new BigDecimal("1.97"));
    }

    @Test
    public void calculate_shouldReturnZero_whenPassingNullValue() {

        final BigDecimal totalCost = pricingService.calculateTotalCost(null, LocalDate.now());

        assertThat(totalCost).isEqualTo(BigDecimal.ZERO);
    }
}