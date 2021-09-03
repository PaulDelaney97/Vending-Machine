package com.pauldelaney.vendingmachine.service;

import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public interface VendingMachineService {

    void checkIfEnoughMoney(Item item, BigDecimal inputMoney) throws
            InsufficientFundsException;

    void removeOneFromItemStockCount(String name) throws
            NoItemInventoryException,
            VendingMachinePersistenceException;

    Map<String, BigDecimal> getInStockItemsAndPrices() throws
            VendingMachinePersistenceException;

    Item getItem(String name, BigDecimal inputMoney) throws
            InsufficientFundsException,
            NoItemInventoryException,
            VendingMachinePersistenceException;

    Map<Integer, Integer> getChangeInCoins(BigDecimal changeDueInPennies);

    BigDecimal getChangeInPennies(Item item, BigDecimal money);

}
