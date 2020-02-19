package com.tech.mai.grocery.repository;

import com.tech.mai.grocery.domain.Discount;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.tech.mai.grocery.repository.DataStore.DISCOUNT_APPLE;
import static com.tech.mai.grocery.repository.DataStore.DISCOUNT_BREAD;

@Repository
public class DiscountRepository {

    private static final Set<Discount> ALL_DISCOUNTS = new HashSet<>(Arrays.asList(DISCOUNT_APPLE, DISCOUNT_BREAD));

    public Set<Discount> getAllDiscounts() { return ALL_DISCOUNTS; }
}
