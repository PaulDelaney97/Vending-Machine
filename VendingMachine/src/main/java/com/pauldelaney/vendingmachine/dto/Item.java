/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pauldelaney.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author pauldelaney
 */
public class Item {

    private String name;        // Name of the item
    private BigDecimal price;   // Price of the item
    private int stockCount;     // How many of this item are in Vending Machine

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String price, int stockCount) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.stockCount = stockCount;
    }

    public String getName() {
        return name;
    }

    // Name is a read only field so we have no setter for name
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.price);
        hash = 13 * hash + this.stockCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.stockCount != other.stockCount) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", price=" + price + ", stockCount=" + stockCount + '}';
    }

}
