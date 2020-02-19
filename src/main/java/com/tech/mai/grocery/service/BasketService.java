package com.tech.mai.grocery.service;

import com.tech.mai.grocery.domain.StockItem;
import com.tech.mai.grocery.dto.Basket;
import com.tech.mai.grocery.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BasketService {

    @Autowired CatalogueRepository catalogueRepository;

    /**
     * Creates a basket containing the given items and calculates the total cost of items
     *
     * @param items Items to add to basket. If an item is not in catalogue it will not be added.
     * @param purchaseDate Date of purchase. If null it will default to today.
     *
     * @return New basket containing given items if in catalogue.
     */
    public Basket createBasket(List<String> items, LocalDate purchaseDate) {

        final Basket basket = new Basket(purchaseDate);

        if (items != null) { items.forEach(item -> addItemToBasket(basket, item)); }

        return basket;
    }

    /**
     * Adds item to basket.
     *
     * @param basket Basket to add given item to.
     * @param item Item to add to given basket.
     *
     * @return Given basket with the addition of given item if in catalogue.
     */
    public Basket addItemToBasket(Basket basket, String item) {

        if (basket == null) { return basket; }

        catalogueRepository.getAllStockItems().stream()
                .filter(stockedItem -> stockedItem.getName().equalsIgnoreCase(item))
                .findAny()
                .ifPresent(basket::addStockItem);

        basket.setTotal(calculateTotalCost(basket.getStockItems()));

        return basket;
    }

    private BigDecimal calculateTotalCost(Map<StockItem, Integer> stockItemsByQuantity) {

        return stockItemsByQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getCost().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
