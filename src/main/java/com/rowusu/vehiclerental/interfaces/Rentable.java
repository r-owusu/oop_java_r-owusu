package com.rowusu.vehiclerental.interfaces;

import com.rowusu.vehiclerental.customers.Customer;

public interface Rentable {
    void rent(Customer customer, int days) throws IllegalStateException;
    void returnVehicle();
}
