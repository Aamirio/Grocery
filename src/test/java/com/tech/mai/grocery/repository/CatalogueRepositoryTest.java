package com.tech.mai.grocery.repository;

import com.tech.mai.grocery.domain.StockItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class CatalogueRepositoryTest {

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Test
    public void getAllStockItems_shouldReturnOnlyStoredStockItems() {

        final Set<StockItem> allStockItems = catalogueRepository.getAllStockItems();

        assertThat(allStockItems.size()).isEqualTo(4);

        allStockItems.forEach(stockItem -> {
            switch (stockItem.getName()) {
                case "soup":  break;
                case "bread": break;
                case "milk":  break;
                case "apple": break;
                default: fail(String.format("%s is an unexpected stock item", stockItem.getName()));
            }
        });
    }
}