package com.pauldelaney.vendingmachine.dao;

/**
 *
 * @author pauldelaney
 */
public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws
            VendingMachinePersistenceException;
}
