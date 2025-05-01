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

        // Quantity spinner with panel that fully contains it
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
        quantityPanel.setBackground(Color.WHITE);
        quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityPanel.setMaximumSize(new Dimension(200, 30));

        JLabel qtyLabel = new JLabel("Qty: ");
        qtyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setMaximumSize(new Dimension(60, 25));
        quantitySpinner.setPreferredSize(new Dimension(60, 25));

        quantityPanel.add(Box.createHorizontalGlue());
        quantityPanel.add(qtyLabel);
        quantityPanel.add(quantitySpinner);
        quantityPanel.add(Box.createHorizontalGlue());

        add(quantityPanel);
        add(Box.createVerticalStrut(5));

        // Add spacer to keep the height consistent with other item panels
        add(Box.createVerticalStrut(48));

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