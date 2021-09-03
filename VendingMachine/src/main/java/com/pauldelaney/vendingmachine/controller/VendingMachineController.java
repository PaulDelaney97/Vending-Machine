package com.pauldelaney.vendingmachine.controller;

import com.pauldelaney.vendingmachine.dao.VendingMachinePersistenceException;
import com.pauldelaney.vendingmachine.dto.Item;
import com.pauldelaney.vendingmachine.service.InsufficientFundsException;
import com.pauldelaney.vendingmachine.service.NoItemInventoryException;
import com.pauldelaney.vendingmachine.service.VendingMachineService;
import com.pauldelaney.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineService service;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        Boolean keepGoing = true;
        String itemSelection = "";
        BigDecimal inputMoney = null;
        Item purchaseItem = null;
        BigDecimal zero = new BigDecimal(0);
        BigDecimal change = null;

        try {
            getVendingMenu();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        inputMoney = getInputMoney();
        while (keepGoing) {

            try {
                itemSelection = getItemSelection();

                if (itemSelection.equals("Exit")) {
                    getChangeInCoins(inputMoney);
                    keepGoing = false;
                    break;
                }
                purchaseItem = getItem(itemSelection, inputMoney);
                change = getChange(purchaseItem, inputMoney);
                getChangeInCoins(change);
                break;

            } catch (InsufficientFundsException | NoItemInventoryException | VendingMachinePersistenceException e) {
                view.displayErrorMessage(e.getMessage());
                view.displayTryAgainMessage();
            }

        }

    }

    private String getItemSelection() {
        return view.getItemSelection();
    }

    private void getVendingMenu() throws VendingMachinePersistenceException {
        Map<String, BigDecimal> map = service.getInStockItemsAndPrices();
        view.displayInStockItems(map);
    }

    private Item getItem(String name, BigDecimal money) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        return service.getItem(name, money);
    }

    private BigDecimal getChange(Item item, BigDecimal money) {
        BigDecimal change = service.getChangeInPennies(item, money);
        view.displayChangeDue(change);
        return change;
    }

    private void getChangeInCoins(BigDecimal changeInPennies) {
        Map<Integer, Integer> changeCoin = service.getChangeInCoins(changeInPennies);
        view.displayChangeDueInCoins(changeCoin);
    }

    private BigDecimal getInputMoney() {
        return view.getInputMoney();
    }

}
