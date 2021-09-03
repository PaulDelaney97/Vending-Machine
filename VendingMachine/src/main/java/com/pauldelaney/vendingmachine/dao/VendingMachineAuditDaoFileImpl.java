/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pauldelaney.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author pauldelaney
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

    private final String AUDIT_FILE;

    public VendingMachineAuditDaoFileImpl() {
        this.AUDIT_FILE = "Audit.txt";
    }

    public VendingMachineAuditDaoFileImpl(String auditTestFile) {
        this.AUDIT_FILE = auditTestFile;
    }

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not write audit"
                    + " information", e);
        }

        LocalDateTime time = LocalDateTime.now();
        out.println(time.toString() + " : " + entry);
        out.flush();
    }

}
