package com.tech.mai.grocery.service;

import com.tech.mai.grocery.domain.StockItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.tech.mai.grocery.fixture.TestFixtures.APPLE;
import static com.tech.mai.grocery.fixture.TestFixtures.MILK;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PricingServiceTest {

    @Autowired
    PricingService pricingService;

    @Test
    public void shouldCalculateTotalCost() {

        final Map<StockItem, Integer> stockItemsByQuantity = new HashMap<>();
        stockItemsByQuantity.put(APPLE, 2);
        stockItemsByQuantity.put(MILK, 1);

        final BigDecimal totalCost = pricingService.calculateTotalCost(stockItemsByQuantity, LocalDate.now());

        assertThat(totalCost).isEqualTo(new BigDecimal("1.50"));
    }
}