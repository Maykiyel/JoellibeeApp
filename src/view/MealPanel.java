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

        // Quantity spinner
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantityPanel.setBackground(Color.WHITE);
        quantityPanel.add(new JLabel("Qty:"));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(54, 20));
        quantityPanel.add(quantitySpinner);

        add(quantityPanel);

        JPanel addonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addonPanel.setBackground(Color.WHITE);

        // Options
        friesCheckBox = new JCheckBox("Add Fries (+₱20)");
        drinkCheckBox = new JCheckBox("Add Drink (+₱25)");

        friesCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        friesCheckBox.setBackground(Color.WHITE);
        friesCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        drinkCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        drinkCheckBox.setBackground(Color.WHITE);
        drinkCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        addonPanel.add(friesCheckBox);
        addonPanel.add(drinkCheckBox);

        add(addonPanel);

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