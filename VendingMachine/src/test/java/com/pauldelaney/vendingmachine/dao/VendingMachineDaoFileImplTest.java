package com.pauldelaney.vendingmachine.dao;

import com.pauldelaney.vendingmachine.dao.VendingMachineDao;
import com.pauldelaney.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "VendingMachineTest.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testGetMapInStockItemsAndPrices() throws VendingMachinePersistenceException {

        // Act
        Map<String, BigDecimal> itemInStock = testDao.getMapInStockItemsAndPrices();
        // Assert
        // There are no doritos in stock so they should not be in this map
        assertFalse(itemInStock.containsKey("Doritos"), "Doritos are out of stock");
        // Check each intstock item is in the map
        assertTrue(itemInStock.containsKey("Irn Bru"), "Irn Bru is in stock");
        assertTrue(itemInStock.containsKey("Smarties"), "Smarties are in stock");
        assertTrue(itemInStock.containsKey("Coke"), "Coke is in stock");
        assertTrue(itemInStock.containsKey("Mars Bar"), "Mars Bar is in stock");
        assertTrue(itemInStock.containsKey("Twirl"), "Twirl is in stock");
        assertTrue(itemInStock.containsKey("Twix"), "Twix is in stock");

        // There are 7 items, doritos is out of stock so there should be 6
        assertEquals(itemInStock.size(), 6, "There are 6 items in stock");

    }

    @Test
    public void testRemoveOneItemFromStockCount() throws VendingMachinePersistenceException {
        // Arrange
        String itemName = "Irn Bru";

        // Act
        int stockCount = testDao.getItemStockCount(itemName);
        testDao.removeOneFromItemStockCount(itemName);
        int newStockCount = testDao.getItemStockCount(itemName);

        // Assert
        assertTrue(stockCount > newStockCount, "Count should be less than before.");
        assertEquals(newStockCount, stockCount - 1, "Count should be one less than before");
    }

    @Test
    public void testGetItemStockCount() throws VendingMachinePersistenceException {
        // Arrange

        Item doritos = new Item("Doritos");
        doritos.setPrice(new BigDecimal("1.50"));
        doritos.setStockCount(0);

        // Act
        int retrievedStockCount = testDao.getItemStockCount("Doritos");
        // Assert
        assertEquals(retrievedStockCount, 0, "There is no Doritos in stock");

    }

    @Test
    public void testGetItem() throws VendingMachinePersistenceException {
        // Arrange
        Item doritos = new Item("Doritos");
        doritos.setPrice(new BigDecimal("1.30"));
        doritos.setStockCount(0); // stock with zero count is still written to file
        // Act
        Item retrievedItem = testDao.getItem("Doritos");
        // Assert
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, doritos, "The item should be Doritos");

    }
}
