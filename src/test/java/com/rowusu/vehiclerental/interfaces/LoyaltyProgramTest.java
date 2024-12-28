package com.rowusu.vehiclerental.interfaces;

import com.rowusu.vehiclerental.customers.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoyaltyProgramTest {

    @Test
    public void testInitialLoyaltyPoints() {
        Customer customer = new Customer("Alice", "C001");
        assertEquals(0, customer.getLoyaltyPoints());
    }

    @Test
    public void testAddLoyaltyPoints() {
        Customer customer = new Customer("Alice", "C001");
        customer.addLoyaltyPoints(20);
        assertEquals(20, customer.getLoyaltyPoints());
    }

    @Test
    public void testAddNegativeLoyaltyPoints() {
        Customer customer = new Customer("Alice", "C001");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customer.addLoyaltyPoints(-10));
        assertEquals("Loyalty points cannot be negative.", exception.getMessage());
    }

    @Test
    public void testLoyaltyStatusBronze() {
        Customer customer = new Customer("Alice", "C001");
        assertEquals("Bronze", customer.getLoyaltyStatus());
    }

    @Test
    public void testLoyaltyStatusSilver() {
        Customer customer = new Customer("Alice", "C001");
        customer.addLoyaltyPoints(50);
        assertEquals("Silver", customer.getLoyaltyStatus());
    }

    @Test
    public void testLoyaltyStatusGold() {
        Customer customer = new Customer("Alice", "C001");
        customer.addLoyaltyPoints(100);
        assertEquals("Gold", customer.getLoyaltyStatus());
    }
}
