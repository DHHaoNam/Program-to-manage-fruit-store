/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.util.ArrayList;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author NamDHHCE180058
 */
public class L03 {

    /**
     * The main method
     *
     * @param args arguments of main method
     */
    public static void main(String[] args) {
        int option;
        Repository store = new Repository();
        Shopping customer = new Shopping();
        CustomerOrders orders = new CustomerOrders();

        boolean continueLoop = true;

        do {
            menu();
            option = IO.getOption(1, 6);

            switch (option) {
                case 1:
                    store.viewRepo();
                    break;
                case 2:
                    store.viewRepo();
                    boolean check = true;
                    while (check) {
                        store.createFruit();
                        store.viewRepo();
                        check = IO.getContinue("Do you want add more fruit(Y/N) ", "Please press Y to continue, N to stop");
                    }
                    break;

                case 3:
                    if (orders.getListOrder().isEmpty()) {
                        System.out.println("No one bought anything");
                    } else {
                        orders.viewOrders();
                    }
                    break;

                case 4:
                    ArrayList<Fruit> activeFruits = store.getActiveFruits();
                    if (activeFruits.isEmpty()) {
                        System.out.println("There are currently no active items in the store. See you later!");
                    } else {
                        customer.setShop(new Repository(activeFruits));
                        customer.buy();
                        if (!customer.getCart().isEmpty()) {
                            orders.addBill(customer.getTime(), new Shopping(customer.getName(), customer.getCart()));
                        }
                    }
                    break;

                case 5:
                    if (store.getStore().isEmpty()) {
                        System.out.println("The store is currently empty. No fruits to activate or disable.");
                    } else {
                        boolean continueDisable = true;
                        while (continueDisable) {
                            store.viewRepo();
                            String fruitId = IO.getIDFruit();
                            Fruit selectedFruit = store.getFruitById(fruitId);
                            if (selectedFruit != null) {
                                if (selectedFruit.isActive()) {
                                    store.disableFruit(fruitId);
                                    System.out.println("Fruit with ID " + fruitId + " has been disabled.");
                                } else {
                                    store.activateFruit(fruitId);
                                    System.out.println("Fruit with ID " + fruitId + " has been activated.");
                                }
                                store.viewRepo();
                                continueDisable = IO.getContinue("Do you want to disable/activate more fruits? (Y/N)", "Please press Y to continue, N to stop");
                            } else {
                                System.out.println("Fruit not found!");
                                continueDisable = IO.getContinue("Do you want to try again? (Y/N)", "Please press Y to try again, N to stop");
                            }
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thanks for your shopping");
                    continueLoop = false; // Exit the do-while loop
                    break;
            }
        } while (continueLoop);
    }

    /**
     * Displays the menu options for the Fruit Shop system. Options include: 1.
     * View Store 2. Create Fruit 3. View orders 4. Shopping (for buyer) 5.
     * Disable/Activate Fruit 6. Exit
     */
    public static void menu() {
        System.out.println("   FRUIT SHOP SYSTEM");
        System.out.println("1. View Store");
        System.out.println("2. Create Fruit");
        System.out.println("3. View orders");
        System.out.println("4. Shopping (for buyer)");
        System.out.println("5. Disable/Activate Fruit");
        System.out.println("6. Exit");
    }
}
