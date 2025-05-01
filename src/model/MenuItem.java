package model;

import java.util.Objects;

public class MenuItem {
    public enum Type {
        MEAL,
        DRINK,
        DESSERT,
        SIDE
    }

    private final String name;
    private final int basePrice;
    private final String imagePath;
    private final Type type;

    public MenuItem(String name, int basePrice, String imagePath, Type type) {
        this.name = name;
        this.basePrice = basePrice;
        this.imagePath = imagePath;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MenuItem menuItem = (MenuItem) o;
        return basePrice == menuItem.basePrice &&
                Objects.equals(name, menuItem.name) &&
                type == menuItem.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePrice, type);
    }
}