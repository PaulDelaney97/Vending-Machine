package com.pauldelaney.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayInStockItems(Map<String, BigDecimal> inStockItemsAndPrices) {
        io.print("===========================================");
        io.print("VENDING MACHINE");
        io.print("===========================================");

        // For each loop uses lambda 'entry' to print out each available
        // item with the associated price
        inStockItemsAndPrices.entrySet().forEach(entry -> {
            io.print(entry.getKey() + ": £" + entry.getValue()
            );
        });

    }

    public String getItemSelection() {
        return io.readString("Please enter the name of your item or 'Exit' "
                + "to leave the vending machine.");
    }

    public BigDecimal getInputMoney() {
        return io.readBigDecimal("Please insert some money: (In Pounds) ");
    }

    public void displayPleaseTakeYourChangeBanner(String name) {
        io.print("Please take your change.");
    }

    public void displayOutOfStockMessage(String name) {
        io.print("Uh oh! This item is out of stock.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayTryAgainMessage() {
        io.print("Please choose something else.");
    }

    public void displayErrorMessage(String errorMessage) {
        io.print(errorMessage);
    }

    public void displayExitMessage() {
        io.print("Good Bye!");
    }

    public void displayChangeDue(BigDecimal changeDue) {
        io.print("You Have: £" + changeDue + " left.");
    }

    public void displayChangeDueInCoins(Map<Integer, Integer> changeDueInCoins) {

        io.print("Here is your change: ");
        changeDueInCoins.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) {
                return;
            }
            if (entry.getKey() == 100) {
                io.print("£" + entry.getKey() / 100 + " x " + entry.getValue());
            } else {
                io.print(entry.getKey() + "p x " + entry.getValue());
            }
        });
    }
}
