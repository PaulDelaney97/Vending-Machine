/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pauldelaney.vendingmachine.dto;

/**
 *
 * @author pauldelaney
 */
public enum Coin {
    // Enum are used to represent a group of constants
    PENNY(1), TWOP(2), FIVEP(5), TENP(10), TWENTYP(20), FIFTYP(50), POUND(100);
    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        switch (this) {
            case PENNY:
                return "1";
            case FIVEP:
                return "5";
            case TENP:
                return "10";
            case TWENTYP:
                return "20";
            case FIFTYP:
                return "50";
            case POUND:
                return "100";
        }
        return null;
    }
}
