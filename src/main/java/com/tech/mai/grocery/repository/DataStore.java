package com.tech.mai.grocery.repository;

import com.tech.mai.grocery.domain.Discount;
import com.tech.mai.grocery.domain.StockItem;

import java.math.BigDecimal;

import static com.tech.mai.grocery.util.DateUtil.*;

/**
 * Mocks a real data store such as a database purely for the purposes of this demo exercise
 */
public class DataStore {

    static final StockItem SOUP = new StockItem("soup", new BigDecimal("0.65"));
    static final StockItem BREAD = new StockItem("bread", new BigDecimal("0.80"));
    static final StockItem MILK = new StockItem("milk", new BigDecimal("1.30"));
    static final StockItem APPLE = new StockItem("apple", new BigDecimal("0.10"));

    static final Discount DISCOUNT_BREAD = new Discount(BREAD, SOUP, 2,yesterday, seventhDayFromYesterday, 0.5);
    static final Discount DISCOUNT_APPLE = new Discount(APPLE, null, 0, threeDaysAfterToday, nextMonthEnd,0.1);
}
