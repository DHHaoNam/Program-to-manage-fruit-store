/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.text.DecimalFormat;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author NamDHHCE180058
 */
public class Fruit {

    private String id;
    private String name;
    private double price;
    private int quantity;
    private String origin;
    private boolean active;
    private static DecimalFormat numberFormatPrice = new DecimalFormat("#,##0.00");

    /**
     * Constructor for Fruit class.
     *
     * @param id the ID of the fruit
     * @param name the name of the fruit
     * @param origin the origin of the fruit
     * @param quantity the quantity of the fruit
     * @param price the price of the fruit
     * @param active the active status of the fruit
     */
    public Fruit(String id, String name, String origin, int quantity, double price, boolean active) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.quantity = quantity;
        this.price = price;
        this.active = active;
    }

    /**
     * Check if the fruit is active.
     *
     * @return true if the fruit is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the active status of the fruit.
     *
     * @param active true to activate the fruit, false to disable it
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get the ID of the fruit.
     *
     * @return the ID of the fruit
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the fruit.
     *
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of the fruit.
     *
     * @return the name of the fruit
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the fruit.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price of the fruit.
     *
     * @return the price of the fruit
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the fruit.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the quantity of the fruit.
     *
     * @return the quantity of the fruit
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the fruit.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the origin of the fruit.
     *
     * @return the origin of the fruit
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the origin of the fruit.
     *
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Format the price of the fruit for display.
     *
     * @return the formatted price of the fruit
     */
    public String formatPrice() {
        return numberFormatPrice.format(price);
    }

    /**
     * Get a formatted string containing information about the fruit.
     *
     * @return a formatted string representation of the fruit
     */
    public String getDisplayInfo() {
        return String.format("| %-4s | %-19s| %-19s|%9s |%10s |", id, name, origin, quantity, "$" + price);
    }

    /**
     * Get a string representation of the fruit object.
     *
     * @return a string representation of the fruit
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.0"); // Format to display up to one decimal place
        return String.format("| %-4s | %-19s| %-19s|%9d |%10s | %-6b |", id, name, origin, quantity, "$" + df.format(price), active);
    }
}
