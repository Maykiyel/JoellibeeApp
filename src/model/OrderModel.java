package model;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private final List<OrderItem> items = new ArrayList<>();
    private OrderType orderType = OrderType.DINE_IN;
    private String deliveryAddress = "";
    private String contactNumber = "";

    // Enum for order types
    public enum OrderType {
        DINE_IN("Dine-in"),
        TAKE_OUT("Take-out"),
        DELIVERY("Delivery");

        private final String display;

        OrderType(String display) {
            this.display = display;
        }

        @Override
        public String toString() {
            return display;
        }
    }

    // Add an item to the order
    public void addItem(OrderItem item) {
        // Check if item already exists to increment quantity
        for (OrderItem existingItem : items) {
            if (existingItem.equals(item)) {
                existingItem.incrementQuantity();
                return;
            }
        }
        items.add(item);
    }

    // Remove an item from the order
    public boolean removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    // Update quantity of an item
    public boolean updateItemQuantity(int index, int quantity) {
        if (index >= 0 && index < items.size() && quantity > 0) {
            items.get(index).setQuantity(quantity);
            return true;
        } else if (quantity <= 0) {
            return removeItem(index);
        }
        return false;
    }

    // Clear all items from the order
    public void clearOrder() {
        items.clear();
    }

    // Calculate the total price of the order
    public int getTotal() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        // Add delivery fee if applicable
        if (orderType == OrderType.DELIVERY) {
            total += 50; // Standard delivery fee
        }

        return total;
    }

    // Get all items in the order
    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    // Get order type
    public OrderType getOrderType() {
        return orderType;
    }

    // Set order type
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    // Get delivery address
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    // Set delivery address
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    // Get contact number
    public String getContactNumber() {
        return contactNumber;
    }

    // Set contact number
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Check if the order is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Get item count
    public int getItemCount() {
        return items.size();
    }
}
