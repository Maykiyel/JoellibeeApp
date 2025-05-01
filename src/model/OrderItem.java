package model;

import java.util.Objects;

public class OrderItem {
    private final MenuItem menuItem;
    private int quantity;
    private final String size;
    private final boolean addFries;
    private final boolean addDrink;

    public OrderItem(MenuItem menuItem, int quantity, String size, boolean addFries, boolean addDrink) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.size = size;
        this.addFries = addFries;
        this.addDrink = addDrink;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public String getSize() {
        return size;
    }

    public boolean hasAddedFries() {
        return addFries;
    }

    public boolean hasAddedDrink() {
        return addDrink;
    }

    public int getUnitPrice() {
        int price = menuItem.getBasePrice();

        // Add price for size upgrade
        if (menuItem.getType() == MenuItem.Type.DRINK) {
            if ("Medium".equals(size)) {
                price += 10;
            } else if ("Large".equals(size)) {
                price += 20;
            }
        }

        // Add price for extras
        if (addFries) {
            price += 20;
        }
        if (addDrink) {
            price += 25;
        }

        return price;
    }

    public int getTotalPrice() {
        return getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(menuItem.getName());

        // Add size for drinks
        if (menuItem.getType() == MenuItem.Type.DRINK) {
            sb.append(" (").append(size).append(")");
        }

        // Add extras
        if (addFries) {
            sb.append(" +Fries");
        }
        if (addDrink) {
            sb.append(" +Drink");
        }

        // Add quantity and price
        sb.append(" x").append(quantity);
        sb.append(" - ").append(CurrencyFormatter.format(getTotalPrice()));

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderItem orderItem = (OrderItem) o;
        return addFries == orderItem.addFries &&
                addDrink == orderItem.addDrink &&
                Objects.equals(menuItem, orderItem.menuItem) &&
                Objects.equals(size, orderItem.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItem, size, addFries, addDrink);
    }
}
