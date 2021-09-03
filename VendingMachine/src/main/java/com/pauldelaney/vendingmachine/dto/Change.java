/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pauldelaney.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author pauldelaney
 */
public class Change {

    // Change due in pennies will be calculated as an Integer, there is no need
    // for BigDecimal as a penny is the lowest denomination of coin we have.
    public static BigDecimal changeDueInPennies(BigDecimal itemPrice, BigDecimal money) {
        BigDecimal changeDueInPennies = (money.subtract(itemPrice));
        return changeDueInPennies;
    }

    public static Map<Integer, Integer> changeDueInCoins(BigDecimal changeDueInPennies) {

        // Create array of Enum Values
        Coin[] coinEnumArray = Coin.values();
        ArrayList<Integer> coins = new ArrayList<>();   //to strings

        for (Coin coin : coinEnumArray) {
            coins.add(coin.getValue());
        }

        // Reverse list so we start with the largest coin
        Collections.reverse(coins);

        // Calculates the correct number of coins for each coin
        Map<Integer, Integer> amountPerCoin = new LinkedHashMap();

        BigDecimal hundred = new BigDecimal("100");
        int change = changeDueInPennies.multiply(hundred).intValue();
        int i = 0;
        int numberOfCoin = 0;

        while (change != 0) {
            numberOfCoin = change / coins.get(i);
            amountPerCoin.put(coins.get(i), numberOfCoin);
            change = change % coins.get(i);
            i++;
        }

        return amountPerCoin;
    }
}
