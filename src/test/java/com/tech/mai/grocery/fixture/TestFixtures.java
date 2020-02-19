package com.tech.mai.grocery.fixture;

import com.tech.mai.grocery.domain.Discount;
import com.tech.mai.grocery.domain.StockItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.tech.mai.grocery.util.DateUtil.*;

public class TestFixtures {

    public static final StockItem SOUP = new StockItem("soup", new BigDecimal("0.65"));
    public static final StockItem BREAD = new StockItem("bread", new BigDecimal("0.80"));
    public static final StockItem MILK = new StockItem("milk", new BigDecimal("1.30"));
    public static final StockItem APPLE = new StockItem("apple", new BigDecimal("0.10"));

    public static final Discount DISCOUNT_BREAD = new Discount(BREAD, SOUP, 2, YESTERDAY, SEVENTH_DAY_FROM_YESTERDAY, 0.5);
    public static final Discount DISCOUNT_APPLE = new Discount(APPLE, null, 0, THREE_DAYS_AFTER_TODAY, NEXT_MONTH_END,0.1);

    public static final Set<StockItem> ALL_STOCK_ITEMS = new HashSet<>(Arrays.asList(SOUP, BREAD, MILK, APPLE));
    public static final Set<Discount> ALL_DISCOUNTS = new HashSet<>(Arrays.asList(DISCOUNT_APPLE, DISCOUNT_BREAD));

}
