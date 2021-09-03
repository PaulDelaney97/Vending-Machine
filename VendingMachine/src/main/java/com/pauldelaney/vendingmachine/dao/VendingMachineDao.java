package com.pauldelaney.vendingmachine.dao;

import com.pauldelaney.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public interface VendingMachineDao {

    /**
     * Removes the given item from the vending machine inventory.Returns the
     * Item object which is being removed or null if there is no Item associated
     * with that name.
     *
     * @param name
     * @throws
     * com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException
     */
    void removeOneFromItemStockCount(String name) throws
            VendingMachinePersistenceException;

    /**
     * Returns a list of all Items in the inventory
     *
     * @return List containing all items in the inventory.
     * @throws
     * com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException
     */
    List<Item> getAllItems() throws VendingMachinePersistenceException;

    /**
     * Returns the number of a particular item in the inventory.If there are no
     * such items, returns 0.
     *
     * @param name
     * @return Stock Count of a particular item
     * @throws
     * com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException
     */
    int getItemStockCount(String name) throws VendingMachinePersistenceException;

    /**
     * Returns the item object associated with the given name.Returns null if no
     * object exists.
     *
     * @param name
     * @return Item associated with given name
     * @throws
     * com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException
     */
    Item getItem(String name) throws VendingMachinePersistenceException;

    /**
     *
     * @return Returns a map of items which are in stock. The key is the name of
     * the item, and the value is the price of the item.
     * @throws
     * com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException
     */
    Map<String, BigDecimal> getMapInStockItemsAndPrices() throws
            VendingMachinePersistenceException;

}
