package com.tech.mai.grocery.domain;

import lombok.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
@Getter
public class StockItem {

    @ToString.Include
    @NonNull private final String name;
    @NonNull private final BigDecimal cost;

}
