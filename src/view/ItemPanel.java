package view;

// Java utility imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Model imports
import model.MenuItem;
import model.OrderItem;
import model.CurrencyFormatter;

public abstract class ItemPanel extends RoundedPanel {
    protected final MenuItem menuItem;
    protected JButton addButton;

    public ItemPanel(MenuItem menuItem) {
        this.menuItem = menuItem;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setupBaseComponents();
    }

    private void setupBaseComponents() {
        // Item name and price
        JLabel nameLabel = new JLabel(menuItem.getName() + " - " + CurrencyFormatter.format(menuItem.getBasePrice()));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Item image
        JLabel imageLabel;
        java.net.URL imgURL = getClass().getResource(menuItem.getImagePath());
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(72, 72, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImage));
        } else {
            imageLabel = new JLabel("Image not found");
        }
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        add(nameLabel);
        add(Box.createVerticalStrut(2));
        add(imageLabel);
        add(Box.createVerticalStrut(2));
    }

    // Create and add the add button
    protected void createAddButton() {
        addButton = new JButton("Add to Cart");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        addButton.setBackground(new Color(230, 0, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(addButton);
    }

    // Set action listener for add button
    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    // Get the menu item
    public MenuItem getMenuItem() {
        return menuItem;
    }

    // Create order item from panel (to be implemented by subclasses)
    public abstract OrderItem createOrderItem();
}