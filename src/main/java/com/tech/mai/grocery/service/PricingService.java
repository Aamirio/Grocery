package com.tech.mai.grocery.service;

import com.tech.mai.grocery.domain.Discount;
import com.tech.mai.grocery.domain.StockItem;
import com.tech.mai.grocery.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Service
public class PricingService {

    @Autowired
    DiscountRepository discountRepository;

    /**
     * Calculates total cost of given stock items factoring in the relevant applicable discounts on the given purchase date.
     *
     * @param stockItemsByQuantity Stock items with their respective quantities
     * @param purchaseDate Date of purchase. If null it will default to today.
     * @return Total cost (to 2dp.)
     */
    BigDecimal calculateTotalCost(Map<StockItem, Integer> stockItemsByQuantity, LocalDate purchaseDate) {

        if (stockItemsByQuantity == null) { return  BigDecimal.ZERO; }

        final BigDecimal totalCost = stockItemsByQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getCost().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal discountAmount = calculateTotalDiscount(stockItemsByQuantity, purchaseDate != null ? purchaseDate : LocalDate.now());

        return totalCost.subtract(discountAmount).setScale(2);
    }

    private BigDecimal calculateTotalDiscount(Map<StockItem, Integer> stockItemsByQuantity, LocalDate purchaseDate) {

        return discountRepository.getAllDiscounts().stream()
                .filter(discount -> isDiscountApplicable(discount, stockItemsByQuantity, purchaseDate))
                .map(discount -> calculateDiscount(discount, stockItemsByQuantity, purchaseDate))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isDiscountApplicable(Discount discount, Map<StockItem, Integer> stockItemsByQuantity, LocalDate purchaseDate) {

        return stockItemsByQuantity.containsKey(discount.getItemOnDiscount()) &&
                (discount.getValidFrom().isEqual(purchaseDate) || discount.getValidFrom().isBefore(purchaseDate)) &&
                (discount.getValidTo().isEqual(purchaseDate) || discount.getValidTo().isAfter(purchaseDate));
    }

    private BigDecimal calculateDiscount(Discount discount, Map<StockItem, Integer> stockItemsByQuantity, LocalDate purchaseDate) {

        BigDecimal totalDiscount = BigDecimal.ZERO;
        final StockItem itemOnDiscount = discount.getItemOnDiscount();
        final StockItem itemDependedOn = discount.getItemDependedOn();
        final Integer quantityItemOnDiscount = stockItemsByQuantity.get(itemOnDiscount);
        final Integer quantityItemDependedOn = stockItemsByQuantity.get(itemDependedOn);
        final BigDecimal discountAmountOnItem = itemOnDiscount.getCost().multiply(BigDecimal.valueOf(discount.getDiscount()));

        if (itemDependedOn == null) {
            totalDiscount = discountAmountOnItem.multiply(new BigDecimal(quantityItemOnDiscount));
        }
        else if (quantityItemDependedOn != null) {
            final BigDecimal noOfDiscountableItems = new BigDecimal(quantityItemDependedOn / discount.getNoOfItemsDependedOn());
            totalDiscount = discountAmountOnItem.multiply(noOfDiscountableItems);
        }

        return totalDiscount;
    }
}
