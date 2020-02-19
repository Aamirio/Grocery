package com.tech.mai.grocery.dto;

import com.tech.mai.grocery.domain.StockItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Basket {

    @Setter
    private BigDecimal total = BigDecimal.ZERO;
    private Map<StockItem, Integer> stockItems = new HashMap<>();
    private LocalDate purchaseDate;

    public Basket(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate != null ? purchaseDate : LocalDate.now();
    }

    public Map<StockItem, Integer> addStockItems(List<StockItem> stockItems) {

        stockItems.forEach(this::addStockItem);

        return this.stockItems;
    }

    public Map<StockItem, Integer> addStockItem(StockItem stockItem){

        this.stockItems.computeIfPresent(stockItem, (key, value) -> ++value);
        this.stockItems.putIfAbsent(stockItem, 1);

        return this.stockItems;
    }

    @Override
    public String toString() {

        final StringBuilder output = new StringBuilder();
        final String lineReturn = "\n";

        return output.append(this.getStockItems().entrySet().stream()
                .map(entry -> String.format("%s x %s", entry.getValue(), entry.getKey().getName()))
                .collect(Collectors.joining(lineReturn, lineReturn, lineReturn)))
                .append(lineReturn)
                .append("Total cost: £")
                .append(this.total)
                .toString();
    }
}
