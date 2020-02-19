package com.tech.mai.grocery.repository;

import com.tech.mai.grocery.domain.Discount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class DiscountRepositoryTest {

    @Autowired
    private DiscountRepository discountRepository;

    @Test
    public void getAllDiscounts_shouldReturnAllDiscountItems() {

        final Set<Discount> allDiscounts = discountRepository.getAllDiscounts();

        assertThat(allDiscounts.size()).isEqualTo(2);

        allDiscounts.forEach(discount -> {
            switch (discount.getItemOnDiscount().getName()) {
                case "bread": break;
                case "apple": break;
                default: fail(String.format("%s is an unexpected discount", discount.getItemOnDiscount().getName()));
            }
        });
    }
}