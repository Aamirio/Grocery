package com.tech.mai.grocery.service;

import com.tech.mai.grocery.domain.StockItem;

import java.math.BigDecimal;

/**
 * Mocks a real data store such as a database purely for the purposes of this demo exercise
 */
public class DataStore {

    static final StockItem SOUP = new StockItem("soup", new BigDecimal("0.65"));
    static final StockItem BREAD = new StockItem("bread", new BigDecimal("0.80"));
    static final StockItem MILK = new StockItem("milk", new BigDecimal("1.30"));
    static final StockItem APPLE = new StockItem("apple", new BigDecimal("0.10"));
}
