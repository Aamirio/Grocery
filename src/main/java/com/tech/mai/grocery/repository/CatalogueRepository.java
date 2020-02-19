package com.tech.mai.grocery.repository;

import com.tech.mai.grocery.domain.StockItem;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.tech.mai.grocery.repository.DataStore.*;

/**
 * Retrieves stock items from data store
 */
@Repository
public class CatalogueRepository {

    private static final Set<StockItem> allStockItems = new HashSet<>(Arrays.asList(SOUP, BREAD, MILK, APPLE));

    /**
     * Retrieves all stock items
     * @return all stock items
     */
    public Set<StockItem> getAllStockItems() { return allStockItems; }
}
