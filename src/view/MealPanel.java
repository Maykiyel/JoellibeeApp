// Panel for meal items
package view;

import model.MenuItem;
import model.OrderItem;

import javax.swing.*;
import java.awt.*;

public class MealPanel extends ItemPanel {
    private final JCheckBox friesCheckBox;
    private final JCheckBox drinkCheckBox;
    private final JSpinner quantitySpinner;

    public MealPanel(MenuItem menuItem) {
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

        // Options panel to contain checkboxes
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.setMaximumSize(new Dimension(200, 60));

        friesCheckBox = new JCheckBox("Add Fries (+₱20)");
        drinkCheckBox = new JCheckBox("Add Drink (+₱25)");

        friesCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        friesCheckBox.setBackground(Color.WHITE);
        friesCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        drinkCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        drinkCheckBox.setBackground(Color.WHITE);
        drinkCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        optionsPanel.add(friesCheckBox);
        optionsPanel.add(drinkCheckBox);

        add(optionsPanel);
        add(Box.createVerticalStrut(5));

        // Add button
        createAddButton();
    }

    @Override
    public OrderItem createOrderItem() {
        return new OrderItem(
                menuItem,
                (Integer) quantitySpinner.getValue(),
                "", // No size for meals
                friesCheckBox.isSelected(),
                drinkCheckBox.isSelected());
    }
}