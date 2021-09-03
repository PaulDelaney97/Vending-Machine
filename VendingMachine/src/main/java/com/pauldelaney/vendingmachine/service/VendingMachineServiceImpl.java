package com.pauldelaney.vendingmachine.service;

import com.pauldelaney.vendingmachine.dao.VendingMachineAuditDao;
import com.pauldelaney.vendingmachine.dao.VendingMachineDao;
import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dto.Change;
import com.pauldelaney.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineAuditDao auditDao;
    private VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineAuditDao auditDao,
            VendingMachineDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    @Override
    public void checkIfEnoughMoney(Item item, BigDecimal inputMoney)
            throws InsufficientFundsException {
        if (item.getPrice().compareTo(inputMoney) == 1) {
            throw new InsufficientFundsException(
                    "ERROR: Insufficient Funds! You only have £" + inputMoney);
        }
    }

    @Override
    public void removeOneFromItemStockCount(String name)
            throws NoItemInventoryException, VendingMachinePersistenceException {
        if (dao.getItemStockCount(name) > 0) {
            dao.removeOneFromItemStockCount(name);
            auditDao.writeAuditEntry("One " + name + " has been removed.");
        } else {
            throw new NoItemInventoryException("Error: " + name + " is out of stock");
        }
    }

    @Override
    public Map<String, BigDecimal> getInStockItemsAndPrices() throws VendingMachinePersistenceException {
        Map<String, BigDecimal> itemsInStockAndPrices = dao.getMapInStockItemsAndPrices();
        return itemsInStockAndPrices;
    }

    @Override
    public Item getItem(String name, BigDecimal inputMoney) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        Item itemToGet = dao.getItem(name);
        if (itemToGet == null) {
            throw new NoItemInventoryException(
                    "ERROR: There is no " + name + "in the machine.");
        }

        checkIfEnoughMoney(itemToGet, inputMoney);
        removeOneFromItemStockCount(name);
        return itemToGet;
    }

    @Override
    public Map<Integer, Integer> getChangeInCoins(BigDecimal changeDueInPennies) {
        return Change.changeDueInCoins(changeDueInPennies);
    }

    @Override
    public BigDecimal getChangeInPennies(Item item, BigDecimal money) {
        return Change.changeDueInPennies(item.getPrice(), money);
    }

}
