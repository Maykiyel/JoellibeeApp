package controller;

import model.MenuItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the menu items available in the application
 */
public class MenuManager {
    private final List<MenuItem> menuItems = new ArrayList<>();

    /**
     * Constructor initializes the menu with default items
     */
    public MenuManager() {
        initializeMenu();
    }

    /**
     * Initializes the menu with predefined items
     */
    private void initializeMenu() {
        // Add meals
        menuItems.add(new MenuItem("Joe Burger", 120, "../img/burger.png", MenuItem.Type.MEAL));
        menuItems.add(new MenuItem("Joelly Spaghetti", 100, "../img/spaghetti.png", MenuItem.Type.MEAL));
        menuItems.add(new MenuItem("Chicken Joe", 150, "../img/chicken.png", MenuItem.Type.MEAL));
        menuItems.add(new MenuItem("Joerrito", 90, "../img/burrito.png", MenuItem.Type.MEAL));
        menuItems.add(new MenuItem("Joelly Hotdog", 75, "../img/hotdog.png", MenuItem.Type.MEAL));

        // Add drinks
        menuItems.add(new MenuItem("Soda", 40, "../img/soda.png", MenuItem.Type.DRINK));
        menuItems.add(new MenuItem("Iced Tea", 50, "../img/icedtea.png", MenuItem.Type.DRINK));
        menuItems.add(new MenuItem("Coffee", 45, "../img/coffee.png", MenuItem.Type.DRINK));

        // Add desserts and sides
        menuItems.add(new MenuItem("Sundae", 35, "../img/sundae.png", MenuItem.Type.DESSERT));
        menuItems.add(new MenuItem("Cone Twirl", 30, "../img/cone.png", MenuItem.Type.DESSERT));
        menuItems.add(new MenuItem("Fries", 25, "../img/fries.png", MenuItem.Type.SIDE));
        menuItems.add(new MenuItem("Joe Nuggets", 115, "../img/nuggets.png", MenuItem.Type.SIDE));

    }

    /**
     * Get all menu items
     * 
     * @return a copy of the menu items list
     */
    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }
}