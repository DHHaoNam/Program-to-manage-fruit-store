/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l03;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * L03 - Create a Java console program to manage a Fruit Shop (Product and
 * Shopping).
 *
 * @author NamDHHCE180058
 */
public class Shopping {

    private String name;
    private LocalDateTime time;
    private Repository shop;
    private ArrayList<Integer> transIndex;
    private ArrayList<Fruit> cart;
    private static final DecimalFormat numberFormat = new DecimalFormat("#,##0.00");

    /**
     * Constructs a new Shopping session.
     */
    public Shopping() {
    }

    /**
     * Constructs a new Shopping session with a specified name and initial cart.
     *
     * @param name the name associated with this shopping session
     * @param cart the initial cart of fruits for this session
     */
    public Shopping(String name, ArrayList<Fruit> cart) {
        this.name = name;
        this.cart = cart;
    }

    /**
     * Retrieves the name associated with this shopping session.
     *
     * @return the name of the shopping session
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name associated with this shopping session.
     *
     * @param name the name to set for the shopping session
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the time of this shopping session.
     *
     * @return the time of the shopping session
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Sets the time of this shopping session.
     *
     * @param time the time to set for the shopping session
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Retrieves the repository (shop) associated with this shopping session.
     *
     * @return the repository (shop) for this shopping session
     */
    public Repository getShop() {
        return shop;
    }

    /**
     * Sets the repository (shop) associated with this shopping session.
     *
     * @param shop the repository (shop) to set for this shopping session
     */
    public void setShop(Repository shop) {
        this.shop = shop;
    }

    /**
     * Retrieves the list of transaction indices for available products in the
     * shop.
     *
     * @return the list of transaction indices
     */
    public ArrayList<Integer> getTransIndex() {
        return transIndex;
    }

    /**
     * Sets the list of transaction indices for available products in the shop.
     *
     * @param transIndex the list of transaction indices to set
     */
    public void setTransIndex(ArrayList<Integer> transIndex) {
        this.transIndex = transIndex;
    }

    /**
     * Retrieves the cart of fruits currently selected for purchase.
     *
     * @return the cart of fruits selected for purchase
     */
    public ArrayList<Fruit> getCart() {
        return cart;
    }

    /**
     * Sets the cart of fruits selected for purchase.
     *
     * @param cart the cart of fruits to set for purchase
     */
    public void setCart(ArrayList<Fruit> cart) {
        this.cart = cart;
    }

    /**
     * Formats a price value into a formatted string.
     *
     * @param n the price value to format
     * @return the formatted price string
     */
    private String formatPrice(double n) {
        return numberFormat.format(n);
    }

    /**
     * Initiates the process of purchasing fruits from the shop. Manages the
     * selection, quantity input, and updating of the shopping cart.
     */
    public void buy() {
        cart = new ArrayList<>(); // Initialize shopping cart
        transIndex = new ArrayList<>(); // Initialize transaction index list
        int countItem = 0; // Counter for items in cart
        int number; // Selected fruit index
        int quantity; // Quantity of fruits to purchase
        translateIndex(); // Populate transaction indices
        boolean isCannotBuyContinue;

        // Main shopping loop
        do {
            // Check if there are available fruits to buy
            if (transIndex.isEmpty()) {
                System.out.println("We sold out. See you again!");
                break;
            }

            while (true) {
                viewShop(); // Display available fruits
                number = IO.getOrder(transIndex.size()); // Get user's fruit selection
                System.out.println("You selected: " + shop.getStore().get(transIndex.get(number - 1)).getName()
                        + " (ID: " + shop.getStore().get(transIndex.get(number - 1)).getId() + ")");

                quantity = IO.getQuantityFruit("Please input quantity: ");

                // Check if selected quantity exceeds available quantity
                if (quantity > shop.getStore().get(transIndex.get(number - 1)).getQuantity()) {
                    // Prompt user if they want to buy the remaining stock
                    if (IO.getContinue("The remaining quantity of this product is " + shop.getStore().get(transIndex.get(number - 1)).getQuantity()
                            + ". Do you want to buy? (Y/N)", "Please press Y to buy, N to do not buy")) {
                        System.out.println("Thank you for purchasing all " + shop.getStore().get(transIndex.get(number - 1)).getName()
                                + " from the store! (Quantity you bought: " + shop.getStore().get(transIndex.get(number - 1)).getQuantity() + ")");

                        // Update existing item in cart if already present
                        boolean isContains = false;
                        for (int i = 0; i < cart.size(); i++) {
                            if (cart.get(i).getId().equalsIgnoreCase(shop.getStore().get(transIndex.get(number - 1)).getId())) {
                                cart.get(i).setQuantity(cart.get(i).getQuantity() + shop.getStore().get(transIndex.get(number - 1)).getQuantity());
                                isContains = true;
                                break;
                            }
                        }

                        // Add new item to cart if not already present
                        if (!isContains) {
                            cart.add(new Fruit(
                                    shop.getStore().get(transIndex.get(number - 1)).getId(),
                                    shop.getStore().get(transIndex.get(number - 1)).getName(),
                                    shop.getStore().get(transIndex.get(number - 1)).getOrigin(),
                                    quantity, // Use the quantity input by user
                                    shop.getStore().get(transIndex.get(number - 1)).getPrice(),
                                    true // Set 'active' default or based on your logic
                            ));
                            cart.get(countItem).setQuantity(shop.getStore().get(transIndex.get(number - 1)).getQuantity());
                            countItem++;
                        }

                        // Update store inventory after purchase
                        shop.getStore().get(transIndex.get(number - 1)).setQuantity(0);
                        transIndex.clear(); // Clear transaction indices
                        translateIndex(); // Update transaction indices
                        break;
                    }
                } else {
                    // Update existing item in cart if already present
                    boolean isContains = false;
                    for (int i = 0; i < cart.size(); i++) {
                        if (cart.get(i).getId().equalsIgnoreCase(shop.getStore().get(transIndex.get(number - 1)).getId())) {
                            cart.get(i).setQuantity(cart.get(i).getQuantity() + quantity);
                            isContains = true;
                            break;
                        }
                    }

                    // Add new item to cart if not already present
                    if (!isContains) {
                        cart.add(new Fruit(
                                shop.getStore().get(transIndex.get(number - 1)).getId(),
                                shop.getStore().get(transIndex.get(number - 1)).getName(),
                                shop.getStore().get(transIndex.get(number - 1)).getOrigin(),
                                quantity,
                                shop.getStore().get(transIndex.get(number - 1)).getPrice(),
                                true // or false depending on your logic for 'active'
                        ));
                        countItem++;
                    }

                    // Update store inventory after purchase
                    if (quantity == shop.getStore().get(transIndex.get(number - 1)).getQuantity()) {
                        shop.getStore().get(transIndex.get(number - 1)).setQuantity(0);
                        transIndex.clear(); // Clear transaction indices
                        translateIndex(); // Update transaction indices
                    } else {
                        shop.getStore().get(transIndex.get(number - 1)).setQuantity(shop.getStore().get(transIndex.get(number - 1)).getQuantity() - quantity);
                    }
                    break;
                }
            }

            // Check if there are remaining fruits to buy or continue shopping
            if (transIndex.isEmpty()) {
                System.out.println("We sold out. See you again!");
                isCannotBuyContinue = true;
            } else {
                isCannotBuyContinue = IO.getContinue("Do you want to order now (Y/N) ", "Please press Y to continue, N to stop");
            }
        } while (!isCannotBuyContinue);

        // If cart is not empty, proceed to view cart and prompt for customer's name
        if (!cart.isEmpty()) {
            viewCart(); // Display shopping cart
            this.name = IO.getName(); // Get customer's name
            time = LocalDateTime.now(); // Record current time of purchase
        }
    }

    /**
     * Displays the current contents of the shopping cart. If the cart is empty,
     * it notifies the user that the cart is empty.
     */
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        } else {
            double sum = 0;
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");
            System.out.println("| No. |  ID  | Fruit Name         | Origin             | Quantity |   Price   |    Amount    |");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");

            int count = 1;
            for (Fruit fruit : cart) {
                sum += fruit.getQuantity() * fruit.getPrice();
                System.out.printf("| %3d ", count++);
                System.out.print(fruit.getDisplayInfo());
                System.out.printf("%13s |\n", "$" + formatPrice(fruit.getQuantity() * fruit.getPrice()));
                System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");
            }
            System.out.printf("|                                                                       TOTAL |%13s |\n", "$" + formatPrice(sum));
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+--------------+");
        }
    }

    /**
     * Displays the current inventory of the shop. If the shop's inventory is
     * empty, it notifies the user that the store is empty.
     */
    public void viewShop() {
        if (shop.getStore().isEmpty()) {
            System.out.println("Store empty");
        } else {
            System.out.println("List of Fruit:");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+");
            System.out.println("| No. |  ID  | Fruit Name         | Origin             | Quantity |   Price   |");
            System.out.println("+-----+------+--------------------+--------------------+----------+-----------+");

            int count = 0;
            for (int i = 0; i < shop.getStore().size(); i++) {
                if (shop.getStore().get(i).getQuantity() == 0) {
                    continue;
                } else {
                    System.out.printf("| %3d ", ++count);
                    System.out.println(shop.getStore().get(i).getDisplayInfo());
                }
                System.out.println("+-----+------+--------------------+--------------------+----------+-----------+");
            }
        }
    }

    /**
     * Populates the transaction indices based on available products in the
     * shop.
     */
    public void translateIndex() {
        for (int i = 0; i < shop.getStore().size(); i++) {
            if (shop.getStore().get(i).getQuantity() != 0) {
                transIndex.add(i);
            }
        }
    }
}
