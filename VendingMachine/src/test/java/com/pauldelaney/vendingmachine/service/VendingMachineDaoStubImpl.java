package com.pauldelaney.vendingmachine.service;

import com.pauldelaney.vendingmachine.dao.VendingMachineDao;
import com.pauldelaney.vendingmachine.dao.VendingMachineDao;
import com.pauldelaney.vendingmachine.dao.VendingMachineDao;
import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        // HardCode an item
        onlyItem = new Item("Irn Bru");
        onlyItem.setPrice(new BigDecimal("1.50"));
        onlyItem.setStockCount(10);
    }

    public VendingMachineDaoStubImpl(Item testItem) {
        this.onlyItem = testItem;
    }

    @Override
    public void removeOneFromItemStockCount(String name) throws VendingMachinePersistenceException {
        if (onlyItem.getName().equals(name)) {
            onlyItem.setStockCount(onlyItem.getStockCount() - 1);
        }
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public int getItemStockCount(String name) throws VendingMachinePersistenceException {
        if (onlyItem.getName().equals(name)) {
            return onlyItem.getStockCount();
        } else {
            return 0;
        }
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        if (onlyItem.getName().equals(name)) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, BigDecimal> getMapInStockItemsAndPrices() throws VendingMachinePersistenceException {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(onlyItem.getName(), onlyItem.getPrice());
        return map;
    }

}
