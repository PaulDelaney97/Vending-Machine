package com.pauldelaney.vendingmachine.dao;

import com.pauldelaney.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<>();
    private final String INVENTORY_FILE;
    public static final String DELIMITER = "::";

    public VendingMachineDaoFileImpl() {
        INVENTORY_FILE = "VendingMachine.txt";
    }

    public VendingMachineDaoFileImpl(String vendingTextFile) {
        INVENTORY_FILE = vendingTextFile;
    }

    @Override
    public Map<String, BigDecimal> getMapInStockItemsAndPrices() throws
            VendingMachinePersistenceException {
        // Returns a list of the items names where the stock count is greater
        // than 0. Gets the keys of the map when stock count > 0 .
        // Stream is filtered to check the count is greater than 0
        loadMachine();
        Map<String, BigDecimal> inStockItems = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getStockCount() > 0)
                .collect(Collectors.toMap(map -> map.getKey(),
                        map -> map.getValue().getPrice()));
        writeMachine();
        return inStockItems;
    }

    @Override
    public void removeOneFromItemStockCount(String name) throws
            VendingMachinePersistenceException {
        loadMachine();
        int prevStockCount = items.get(name).getStockCount();
        items.get(name).setStockCount(prevStockCount - 1);
        writeMachine();
    }

    @Override
    public List<Item> getAllItems() throws
            VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList(items.values());
    }

    @Override
    public int getItemStockCount(String name) throws
            VendingMachinePersistenceException {
        loadMachine();
        Item itemToCount = items.get(name);
        return itemToCount.getStockCount();
    }

    @Override
    public Item getItem(String name) throws
            VendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
    }

    private String marshallItem(Item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getPrice() + DELIMITER;
        itemAsText += anItem.getStockCount();
        return itemAsText;
    }

    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String name = itemTokens[0];
        Item itemFromFile = new Item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setPrice(bigDecimal);
        itemFromFile.setStockCount(Integer.parseInt(itemTokens[2]));
        return itemFromFile;
    }

    private void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load item data into memory.", e);
        }
        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    private void writeMachine() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save student data.", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

}
