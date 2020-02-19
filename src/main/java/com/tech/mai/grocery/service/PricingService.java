package com.tech.mai.grocery.service;

import com.tech.mai.grocery.domain.StockItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class PricingService {

    /**
     * Calculates total cost of given stock items.
     *
     * @param stockItemsByQuantity Stock items with their respective quantities.
     * @param purchaseDate Date of purchase. If null it will default to today.
     * @return Total cost (to 2dp.)
     */
    BigDecimal calculateTotalCost(Map<StockItem, Integer> stockItemsByQuantity, LocalDate purchaseDate) {

        return stockItemsByQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getCost().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
