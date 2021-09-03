/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pauldelaney.vendingmachine.service;

import com.pauldelaney.vendingmachine.dao.VendingMachineAuditDao;
import com.pauldelaney.vendingmachine.dao.VendingMachineDao;
import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineServiceImplTest {

    private VendingMachineService service;
    private VendingMachineDao testDao;
    private VendingMachineAuditDao testAuditDao;

    public VendingMachineServiceImplTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", VendingMachineService.class);
    }

    @Test
    public void testCheckIfEnoughMoney() {
        // Arrange
        Item irnBru = new Item("Irn Bru");
        irnBru.setPrice(new BigDecimal("1.50"));
        irnBru.setStockCount(10);

        BigDecimal enoughMoney = new BigDecimal("2.00");
        BigDecimal notEnoughMoney = new BigDecimal("1.00");

        // Act - enough money
        try {
            service.checkIfEnoughMoney(irnBru, enoughMoney);
        } catch (InsufficientFundsException e) {
            fail("There is enough money, exception should not have been thrown");
        }

        // Act - not enough money
        try {
            service.checkIfEnoughMoney(irnBru, notEnoughMoney);
            fail("There is not enough money, exception should have been thrown");
        } catch (InsufficientFundsException e) {

        }
    }

    @Test
    public void testRemoveOneFromItemStockCount() throws VendingMachinePersistenceException {
        String name = "Doritos"; // Doritos are out of stock

        try { // Act
            service.removeOneFromItemStockCount(name);
            // Assert
            fail("There are no Doritos in machine, exception should have been thrown.");
        } catch (NoItemInventoryException e) {

        }

        String irnBru = "Irn Bru";

        try {
            // Act
            service.removeOneFromItemStockCount(irnBru);
        } catch (NoItemInventoryException e) {
            if (testDao.getItemStockCount(irnBru) > 0) {
                fail("Irn Bru is in stock, exception should not have been thrown.");
            }
        }

    }

    @Test
    public void testGetItem() throws VendingMachinePersistenceException, InsufficientFundsException {
        // Arrange

        Item doritos = new Item("Doritos");
        doritos.setPrice(new BigDecimal("1.30"));
        doritos.setStockCount(0);
        BigDecimal money = new BigDecimal("3");

        Item irnBru = new Item("Irn Bru");
        irnBru.setPrice(new BigDecimal("1.50"));
        irnBru.setStockCount(10);

        Item itemToGet = null;

        try {
            itemToGet = service.getItem("Doritis", money);
            fail("This item is out of stock.");
        } catch (NoItemInventoryException e) {

        }

        try {
            itemToGet = service.getItem("Irn Bru", money);
        } catch (NoItemInventoryException e) {
            fail("The item is in stock.");
        }

    }

    @Test
    public void testGetChange() {
        Item irnBru = new Item("Irn Bru");
        irnBru.setPrice(new BigDecimal("1.50"));
        irnBru.setStockCount(10);

        BigDecimal money = new BigDecimal("4.49");

        // Change in pennies should be 2.99
        BigDecimal change = service.getChangeInPennies(irnBru, money);

        BigDecimal calculatedChange = money.subtract(irnBru.getPrice());

        assertEquals(change, calculatedChange, "The amounts should be equal.");

        // Change should be £1 x 2, 50p x 1, 20p x 2, 5p x1 , 2p x2
        Map<Integer, Integer> expectedChange = new LinkedHashMap<>();

        expectedChange.put(100, 2);
        expectedChange.put(50, 1);
        expectedChange.put(20, 2);
        expectedChange.put(10, 0); // Map holds this coin even tho value is zero
        expectedChange.put(5, 1);
        expectedChange.put(2, 2);

        Map<Integer, Integer> changeMap = service.getChangeInCoins(change);

        assertEquals(expectedChange.size(), 6, "There are 6 differnt types of coins in map.");
        assertEquals(expectedChange, changeMap, "The maps should be the same.");

    }

}
