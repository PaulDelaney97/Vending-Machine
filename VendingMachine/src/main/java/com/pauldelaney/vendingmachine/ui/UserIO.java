package com.pauldelaney.vendingmachine.ui;

import java.math.BigDecimal;

/**
 *
 * @author pauldelaney
 */
public interface UserIO {

    // This is the interface for us User I/O
    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    BigDecimal readBigDecimal(String please_insert_some_money_);
}
