package com.pauldelaney.vendingmachine.service;

/**
 *
 * @author pauldelaney
 */
public class NoItemInventoryException extends Exception {

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
