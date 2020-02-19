package com.tech.mai.grocery.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Discount {

    private StockItem itemOnDiscount;
    private StockItem itemDependedOn;
    private Integer noOfItemsDependedOn;
    private LocalDate validFrom;
    private LocalDate validTo;
    private double discount;
}
