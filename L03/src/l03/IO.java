/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.util.Scanner;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author NamDHHCE180058
 */
public class IO {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Get an integer option from the user within a specified range.
     *
     * @param min the minimum allowed option
     * @param max the maximum allowed option
     * @return the user's selected option
     */
    public static int getOption(int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print("Please choose: "); // Prompt user to choose an option
                n = Integer.parseInt(sc.nextLine().trim()); // Read user input and parse it to an integer
                if (n < min || n > max) { // Check if the input is within the specified range
                    throw new NumberFormatException(); // Throw exception if not in range
                }
                return n; // Return valid user input
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number from " + min + " - " + max); // Error message for invalid input
            }
        }
    }

    /**
     * Prompt the user to select either to activate or disable a fruit.
     *
     * @return 1 for activate, 2 for disable
     */
    public static int getActiveOrDisableOption() {
        int n;
        while (true) {
            try {
                System.out.println("1. Active"); // Display option 1: Active
                System.out.println("2. Disable"); // Display option 2: Disable
                System.out.print("Select an option: "); // Prompt user to select an option
                n = Integer.parseInt(sc.nextLine().trim()); // Read user input and parse it to an integer
                if (n != 1 && n != 2) { // Check if the input is either 1 or 2
                    throw new NumberFormatException(); // Throw exception if not 1 or 2
                }
                return n; // Return valid user input
            } catch (NumberFormatException e) {
                System.out.println("Please enter either 1 or 2"); // Error message for invalid input
            }
        }
    }

    /**
     * Get the ID of a fruit from the user.
     *
     * @return the entered ID
     */
    public static String getIDFruit() {
        String s;
        while (true) {
            try {
                System.out.print("ID fruit: "); // Prompt user to enter fruit ID
                s = sc.nextLine().trim(); // Read and trim user input
                if (s.isEmpty()) { // Check if ID is empty
                    throw new Exception("ID of fruit cannot be empty!"); // Throw exception for empty ID
                } else if (s.length() != 4) { // Check if ID length is not 4 characters
                    throw new Exception("ID of fruit must be 4 characters!"); // Throw exception for incorrect ID length
                }
                return s; // Return valid ID
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Print error message
            }
        }
    }

    /**
     * Get the name of a fruit from the user.
     *
     * @return the entered name
     */
    public static String getNameFruit() {
        String s;
        while (true) {
            try {
                System.out.print("Name fruit: "); // Prompt user to enter fruit name
                s = sc.nextLine().trim(); // Read and trim user input
                if (s.isEmpty()) { // Check if name is empty
                    throw new Exception(); // Throw exception for empty name
                }
                return s; // Return valid name
            } catch (Exception e) {
                System.out.println("Name cannot be empty"); // Error message for empty name
            }
        }
    }

    /**
     * Get the price of a fruit from the user.
     *
     * @param msg the message prompt for entering price
     * @return the entered price
     */
    public static double getPriceFruit(String msg) {
        double n;
        while (true) {
            try {
                System.out.print(msg); // Prompt user with custom message to enter fruit price
                n = Double.parseDouble(sc.nextLine().trim()); // Read and parse user input to double
                if (n <= 0) { // Check if price is less than or equal to zero
                    throw new NumberFormatException(); // Throw exception for non-positive price
                }
                return n; // Return valid price
            } catch (NumberFormatException e) {
                System.out.println("Price of fruit must be an integer number greater than 0"); // Error message for invalid price
            }
        }
    }

    /**
     * Get the quantity of a fruit from the user.
     *
     * @param msg the message prompt for entering quantity
     * @return the entered quantity
     */
    public static int getQuantityFruit(String msg) {
        int n;
        while (true) {
            try {
                System.out.print(msg); // Prompt user with custom message to enter fruit quantity
                n = Integer.parseInt(sc.nextLine().trim()); // Read and parse user input to integer
                if (n <= 0) { // Check if quantity is less than or equal to zero
                    throw new NumberFormatException(); // Throw exception for non-positive quantity
                }
                return n; // Return valid quantity
            } catch (NumberFormatException e) {
                System.out.println("Quantity of fruit must be an integer number greater than 0"); // Error message for invalid quantity
            }
        }
    }

    /**
     * Get the origin of a fruit from the user.
     *
     * @return the entered origin
     */
    public static String getOriginFruit() {
        String s;
        while (true) {
            try {
                System.out.print("Origin fruit: "); // Prompt user to enter fruit origin
                s = sc.nextLine().trim(); // Read and trim user input
                if (s.isEmpty()) { // Check if origin is empty
                    throw new Exception("Name cannot be empty"); // Throw exception for empty origin
                } else {
                    char c[] = s.toCharArray();
                    for (char d : c) {
                        if (!Character.isLetter(d)) { // Check if origin contains only letters
                            throw new Exception("Name of country only contain letters"); // Throw exception for invalid characters
                        }
                    }
                }
                return s; // Return valid origin
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Print error message
            }
        }
    }

    /**
     * Get user's choice to continue (Y) or stop (N) a process.
     *
     * @param msg the message prompt for continuing
     * @param err the error message for invalid input
     * @return true if user chooses to continue, false otherwise
     */
    public static boolean getContinue(String msg, String err) {
        String s;
        while (true) {
            try {
                System.out.print(msg); // Prompt user with custom message to continue
                s = sc.nextLine().trim(); // Read and trim user input
                if (s.isEmpty()) { // Check if input is empty
                    throw new Exception(); // Throw exception for empty input
                } else if (!s.equalsIgnoreCase("Y") && !s.equalsIgnoreCase("N")) { // Check if input is neither "Y" nor "N"
                    throw new Exception(); // Throw exception for invalid input
                } else {
                    if (s.equalsIgnoreCase("Y")) { // Check if input is "Y"
                        return true; // Return true for continue
                    } else if (s.equalsIgnoreCase("N")) { // Check if input is "N"
                        return false; // Return false for stop
                    }
                }
            } catch (Exception e) {
                System.out.println(err); // Print error message
            }
        }
    }

    /**
     * Get the name from the user.
     *
     * @return the entered name
     */
    public static String getName() {
        String s;
        while (true) {
            try {
                System.out.print("Input your name: "); // Prompt user to enter their name
                s = sc.nextLine().trim(); // Read and trim user input
                char c[] = s.toCharArray();

                if (s.isEmpty()) { // Check if name is empty
                    throw new Exception("Name cannot be empty"); // Throw exception for empty name
                } else {
                    for (char d : c) {
                        if (!Character.isLetter(d) && !Character.isWhitespace(d)) { // Check if name contains only letters and spaces
                            throw new Exception("Name must include letters and may include spaces"); // Throw exception for invalid characters
                        }
                    }
                }
                return s; // Return valid name
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Print error message
            }
        }
    }

    /**
     * Get the order from the user within the specified bound.
     *
     * @param bound the maximum number of orders
     * @return the user's selected order
     */
    public static int getOrder(int bound) {
        int n;
        while (true) {
            try {
                System.out.print("Choose product number in the table: "); // Prompt user to choose product number
                n = Integer.parseInt(sc.nextLine().trim()); // Read and parse user input to integer
                if (n <= 0 || n > bound) { // Check if order number is within valid range
                    throw new NumberFormatException(); // Throw exception for invalid order number
                }
                return n; // Return valid order number
            } catch (NumberFormatException e) {
                System.out.println("Please choose a numerical order in the table"); // Error message for invalid input
            }
        }
    }
}
