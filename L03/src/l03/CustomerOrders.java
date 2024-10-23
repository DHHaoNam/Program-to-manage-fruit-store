/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author NamDHHCE180058
 */
public class CustomerOrders {

    private Map<LocalDateTime, Shopping> listOrder; // Map to store orders with timestamps
    private List<Map.Entry<LocalDateTime, Shopping>> sortedEntries; // Sorted list of order entries
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'\nDate: 'MM-dd-yyyy'\nTime: 'HH:mm:ss.SSS");

    /**
     * Constructs an empty CustomerOrders collection.
     */
    public CustomerOrders() {
        listOrder = new Hashtable<>(); // Initialize the map of orders
    }

    /**
     * Constructs a CustomerOrders collection with specified orders and sorted
     * entries.
     *
     * @param listOrder the map of orders with timestamps
     * @param sortedEntries the sorted list of order entries
     */
    public CustomerOrders(Map<LocalDateTime, Shopping> listOrder, List<Map.Entry<LocalDateTime, Shopping>> sortedEntries) {
        this.listOrder = listOrder; // Initialize with provided map of orders
        this.sortedEntries = sortedEntries; // Initialize with provided sorted list of entries
    }

    /**
     * Retrieves the map of orders with timestamps.
     *
     * @return the map of orders with timestamps
     */
    public Map<LocalDateTime, Shopping> getListOrder() {
        return listOrder; // Return the map of orders
    }

    /**
     * Sets the map of orders with timestamps.
     *
     * @param listOrder the map of orders with timestamps to set
     */
    public void setListOrder(Map<LocalDateTime, Shopping> listOrder) {
        this.listOrder = listOrder; // Set the map of orders
    }

    /**
     * Retrieves the sorted list of order entries.
     *
     * @return the sorted list of order entries
     */
    public List<Map.Entry<LocalDateTime, Shopping>> getSortedEntries() {
        return sortedEntries; // Return the sorted list of order entries
    }

    /**
     * Sets the sorted list of order entries.
     *
     * @param sortedEntries the sorted list of order entries to set
     */
    public void setSortedEntries(List<Map.Entry<LocalDateTime, Shopping>> sortedEntries) {
        this.sortedEntries = sortedEntries; // Set the sorted list of order entries
    }

    /**
     * Adds a new customer order to the collection.
     *
     * @param time the timestamp of the order
     * @param customer the Shopping instance representing the customer's order
     */
    public void addBill(LocalDateTime time, Shopping customer) {
        listOrder.put(time, customer); // Add a new order to the map with timestamp
    }

    /**
     * Displays all customer orders sorted by order time. If no orders are
     * present, notifies that no orders have been placed yet.
     */
    public void viewOrders() {
        if (listOrder.isEmpty()) {
            System.out.println("No orders have been placed yet."); // Display message if no orders
        } else {
            this.sortedEntries = sortOrdersByTime(); // Sort orders by timestamp
            displaySortedOrders(); // Display sorted orders
        }
    }

    /**
     * Sorts the orders by timestamp (descending order).
     *
     * @return the sorted list of order entries
     */
    private List<Map.Entry<LocalDateTime, Shopping>> sortOrdersByTime() {
        sortedEntries = new ArrayList<>(listOrder.entrySet()); // Create a new list from map entries
        sortedEntries.sort(Collections.reverseOrder(Map.Entry.comparingByKey())); // Sort entries in descending order by timestamp
        return sortedEntries; // Return sorted list of order entries
    }

    /**
     * Displays the sorted customer orders along with order details.
     */
    private void displaySortedOrders() {
        for (Map.Entry<LocalDateTime, Shopping> entry : sortedEntries) {
            LocalDateTime orderTime = entry.getKey(); // Get order timestamp
            Shopping shopping = entry.getValue(); // Get Shopping instance for order

            System.out.println("ORDER TIME:" + orderTime.format(formatter)); // Display formatted order time
            System.out.println("CUSTOMER: " + shopping.getName()); // Display customer name
            shopping.viewCart(); // Display shopping cart details
            System.out.println(); // Empty line for clarity
        }
    }
}
