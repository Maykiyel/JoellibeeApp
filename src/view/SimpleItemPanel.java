// Panel for simple items (desserts, sides)
package view;

import model.MenuItem;
import model.OrderItem;

import javax.swing.*;
import java.awt.*;

public class SimpleItemPanel extends ItemPanel {
    private final JSpinner quantitySpinner;

    public SimpleItemPanel(MenuItem menuItem) {
        super(menuItem);

        // Quantity spinner
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantityPanel.setBackground(Color.WHITE);
        quantityPanel.add(new JLabel("Qty:"));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(50, 25));
        quantityPanel.add(quantitySpinner);

        add(quantityPanel);

        // Add button
        createAddButton();
    }

    @Override
    public OrderItem createOrderItem() {
        return new OrderItem(
                menuItem,
                (Integer) quantitySpinner.getValue(),
                "", // No size for simple items
                false, // No fries for simple items
                false // No drink for simple items
        );
    }
}