package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.util.List;

/**
 * Controller class that coordinates between the model and view
 */
public class OrderController {
    private final OrderModel model;
    private final MainView view;
    private final MenuManager menuManager;

    /**
     * Constructor initializes the controller with model and view
     * 
     * @param model the order model
     * @param view  the main view
     */
    public OrderController(OrderModel model, MainView view) {
        this.model = model;
        this.view = view;
        this.menuManager = new MenuManager();

        initializeMenu();
        setupEventHandlers();
    }

    /**
     * Initialize the menu by creating panels for each menu item
     */
    private void initializeMenu() {
        // Create menu items
        List<MenuItem> menuItems = menuManager.getMenuItems();

        // Create item panels based on menu item type
        for (MenuItem item : menuItems) {
            ItemPanel panel;

            switch (item.getType()) {
                case MEAL:
                    panel = new MealPanel(item);
                    break;
                case DRINK:
                    panel = new DrinkPanel(item);
                    break;
                default:
                    panel = new SimpleItemPanel(item);
                    break;
            }

            // Add item to cart when "Add to Cart" button is clicked
            panel.setAddButtonListener(e -> addToCart(panel));

            // Add panel to view
            view.addItemPanel(panel);
        }
    }

    /**
     * Set up event handlers for UI elements
     */
    private void setupEventHandlers() {
        // Handle order type selection
        view.setOrderTypeListener(e -> {
            OrderModel.OrderType type = view.getSelectedOrderType();
            model.setOrderType(type);

            // Show delivery fields if delivery is selected
            view.showDeliveryFields(type == OrderModel.OrderType.DELIVERY);

            // Update cart to reflect potential delivery fee
            updateCartDisplay();
        });

        // Handle cancel button
        view.setCancelButtonListener(e -> {
            if (model.isEmpty()) {
                view.showMessage("Cart is already empty", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int result = view.showConfirmDialog("Clear the cart?", "Confirm");
            if (result == JOptionPane.YES_OPTION) {
                model.clearOrder();
                updateCartDisplay();
            }
        });

        // Handle checkout button
        view.setCheckoutButtonListener(e -> {
            if (model.isEmpty()) {
                view.showMessage("Cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if address is entered for delivery orders
            if (model.getOrderType() == OrderModel.OrderType.DELIVERY) {
                String address = view.getDeliveryAddress();
                String contact = view.getContactNumber();

                if (address.trim().isEmpty()) {
                    view.showMessage("Please enter a delivery address", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (contact.trim().isEmpty()) {
                    view.showMessage("Please enter a contact number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Set delivery information in model
                model.setDeliveryAddress(address);
                model.setContactNumber(contact);
            }

            // Show order summary
            StringBuilder summary = new StringBuilder("Order Summary:\n\n");

            for (OrderItem item : model.getItems()) {
                summary.append(item.toString()).append("\n");
            }

            summary.append("\nTotal: ").append(CurrencyFormatter.format(model.getTotal()));
            summary.append("\nOrder Type: ").append(model.getOrderType());

            if (model.getOrderType() == OrderModel.OrderType.DELIVERY) {
                summary.append("\nDelivery Address: ").append(model.getDeliveryAddress());
                summary.append("\nContact Number: ").append(model.getContactNumber());
            }

            view.showMessage(summary.toString(), "Checkout", JOptionPane.INFORMATION_MESSAGE);

            // Clear the cart after checkout
            model.clearOrder();
            updateCartDisplay();
        });
    }

    /**
     * Add an item to the cart from a panel
     * 
     * @param panel the panel containing the item to add
     */
    private void addToCart(ItemPanel panel) {
        OrderItem item = panel.createOrderItem();
        model.addItem(item);
        updateCartDisplay();
    }

    /**
     * Update the cart display in the view
     */
    private void updateCartDisplay() {
        view.updateCart(model.getItems(), model.getTotal());
    }
}