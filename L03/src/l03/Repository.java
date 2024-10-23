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
public class Repository {

    private ArrayList<Fruit> store; // List to store fruits
    private boolean forShopping; // Flag to indicate if shopping mode is active

    /**
     * Constructor to initialize an empty repository and set shopping mode to
     * false.
     */
    public Repository() {
        store = new ArrayList<>();
        store.add(new Fruit("C001", "Cam", "VN", 141, 3, true));
        store.add(new Fruit("Q002", "Quyt", "VN", 132, 5, true));
        store.add(new Fruit("K003", "Kiwi", "US", 153, 10, true));
        forShopping = false;
    }

    /**
     * Constructor to initialize repository with a provided list of fruits.
     *
     * @param store The list of fruits to initialize the repository.
     */
    public Repository(ArrayList<Fruit> store) {
        this.store = store;
        forShopping = false;
    }

    /**
     * Check if shopping mode is active.
     *
     * @return true if shopping mode is active, false otherwise.
     */
    public boolean isForShopping() {
        return forShopping;
    }

    /**
     * Set the shopping mode.
     *
     * @param forShopping true to activate shopping mode, false otherwise.
     */
    public void setForShopping(boolean forShopping) {
        this.forShopping = forShopping;
    }

    /**
     * Get the list of fruits in the repository.
     *
     * @return The list of fruits.
     */
    public ArrayList<Fruit> getStore() {
        return store;
    }

    /**
     * Set the list of fruits in the repository.
     *
     * @param store The list of fruits to set.
     */
    public void setStore(ArrayList<Fruit> store) {
        this.store = store;
    }

    /**
     * Method to create a new fruit and add it to the repository. If a fruit
     * with the same ID exists, it prompts the user to update it. If a fruit
     * with the same name and origin but a different ID exists and is active, it
     * prompts the user to update it.
     */
    public void createFruit() {
        String id = IO.getIDFruit();

        // Check if a fruit with the same ID already exists
        int idIndex = isIDExisted(id);

        // If a fruit with the same ID exists and is active
        if (idIndex != -1) {
            if (!store.get(idIndex).isActive()) {
                System.out.println("A fruit with the same ID exists but is inactive. Cannot add or update this fruit.");
                return; // Exit the method
            } else {
                System.out.println("A fruit with the same ID already exists.");
                // Prompt user if they want to update the existing fruit
                if (IO.getContinue("Do you want to update this fruit? (Y/N)", "Please press Y to update, N to cancel")) {
                    // If user wants to update the quantity
                    if (IO.getContinue("Do you want to update the quantity? (Y/N)", "Please press Y to update quantity, N to skip")) {
                        int a;
                        a = IO.getQuantityFruit("Enter new quantity: ");
                        store.get(idIndex).setQuantity(store.get(idIndex).getQuantity() + a);
                    }
                    // If user wants to update the price
                    if (IO.getContinue("Do you want to update the price? (Y/N)", "Please press Y to update price, N to skip")) {
                        store.get(idIndex).setPrice(IO.getPriceFruit("Enter new price: "));
                    }
                }
                return; // Exit the method after updating
            }
        }

        // Check if a fruit with the same name and origin already exists but with a different ID
        String name = IO.getNameFruit();
        String origin = IO.getOriginFruit();
        boolean sameNameOriginExists = false;
        int fruitIndex = -1;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getName().equalsIgnoreCase(name)
                    && store.get(i).getOrigin().equalsIgnoreCase(origin)) {
                sameNameOriginExists = true;
                fruitIndex = i;
                break;
            }
        }

        // If a fruit with the same name and origin exists
        if (sameNameOriginExists) {
            if (!store.get(fruitIndex).isActive()) {
                System.out.println("A fruit with the same name and origin exists but is inactive. Cannot add or update this fruit.");
                return; // Exit the method
            } else {
                System.out.println("A fruit with the same name and origin already exists.");
                // Prompt user if they want to update the existing fruit
                if (IO.getContinue("Do you want to update this fruit? (Y/N)", "Please press Y to update, N to cancel")) {
                    // If user wants to update the quantity
                    if (IO.getContinue("Do you want to update the quantity? (Y/N)", "Please press Y to update quantity, N to skip")) {
                        store.get(fruitIndex).setQuantity(IO.getQuantityFruit("Enter new quantity: "));
                    }
                    // If user wants to update the price
                    if (IO.getContinue("Do you want to update the price? (Y/N)", "Please press Y to update price, N to skip")) {
                        store.get(fruitIndex).setPrice(IO.getPriceFruit("Enter new price: "));
                    }
                }
            }
        } else {
            // Add new fruit to the repository if no duplicates are found
            store.add(new Fruit(id, name, origin, IO.getQuantityFruit("Quantity fruit: "), IO.getPriceFruit("Price fruit: "), true));
        }
    }

    /**
     * Check if a fruit with the same name and origin already exists in the
     * repository.
     *
     * @param name The name of the fruit.
     * @param origin The origin of the fruit.
     * @return The index of the fruit if found, otherwise -1.
     */
    public int isTypeExisted(String name, String origin) {
        if (!store.isEmpty()) {
            for (int i = 0; i < store.size(); i++) {
                if (store.get(i).getName().equalsIgnoreCase(name) && store.get(i).getOrigin().equalsIgnoreCase(origin)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Check if a fruit with the same ID already exists in the repository.
     *
     * @param id The ID of the fruit.
     * @return The index of the fruit if found, otherwise -1.
     */
    public int isIDExisted(String id) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Display the list of fruits in the repository.
     */
    public void viewRepo() {
        if (store.isEmpty()) {
            System.out.println("Store is empty.");
        } else {
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------+");
            System.out.println("| No. |  ID  | Fruit Name         | Origin             | Quantity |   Price   | Active |");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------+");

            int count = 1;
            for (Fruit fruit : store) {
                if (!fruit.isActive() && forShopping) {
                    continue; // Skip disabled fruits if in shopping mode
                }

                System.out.printf("| %3d ", count++);
                if (!fruit.isActive()) {
                    System.out.printf("| %-4s | %-19s| %-19s|%9s |%10s | %-6b |\n", fruit.getId(), fruit.getName(), fruit.getOrigin(), fruit.getQuantity(), "$" + fruit.getPrice(), fruit.isActive());
                } else {
                    System.out.println(fruit.toString());
                }
                System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------+");
            }
        }
    }

    /**
     * Disable a fruit in the repository based on ID.
     *
     * @param id The ID of the fruit to disable.
     */
    public void disableFruit(String id) {
        int index = isIDExisted(id);
        if (index != -1) {
            store.get(index).setActive(false);
        } else {
            System.out.println("Fruit not found!");
        }
    }

    /**
     * Activate a fruit in the repository based on ID.
     *
     * @param id The ID of the fruit to activate.
     */
    public void activateFruit(String id) {
        int index = isIDExisted(id);
        if (index != -1) {
            store.get(index).setActive(true);
        } else {
            System.out.println("Fruit not found!");
        }
    }

    /**
     * Get a list of active fruits in the repository.
     *
     * @return The list of active fruits.
     */
    public ArrayList<Fruit> getActiveFruits() {
        ArrayList<Fruit> activeFruits = new ArrayList<>();
        for (Fruit fruit : store) {
            if (fruit.isActive()) {
                activeFruits.add(fruit);
            }
        }
        return activeFruits;
    }

    /**
     * Get a fruit from the repository based on ID.
     *
     * @param id The ID of the fruit to retrieve.
     * @return The fruit object if found, otherwise null.
     */
    public Fruit getFruitById(String id) {
        int index = isIDExisted(id);
        if (index != -1) {
            return store.get(index);
        }
        return null;
    }

    /**
     * Remove a fruit from the repository based on index.
     *
     * @param i The index of the fruit to remove.
     */
    public void removeFruit(int i) {
        store.remove(i);
    }

    /**
     * Activate shopping mode and view the repository.
     */
    public void purchaseFruit() {
        setForShopping(true); // Set shopping mode to true
        viewRepo(); // Display repository contents
    }
}
