// Panel for drink items
package view;

import model.MenuItem;
import model.OrderItem;

import javax.swing.*;
import java.awt.*;

public class DrinkPanel extends ItemPanel {
    private final JRadioButton smallRadio;
    private final JRadioButton mediumRadio;
    private final JRadioButton largeRadio;
    private final JSpinner quantitySpinner;

    public DrinkPanel(MenuItem menuItem) {
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

        // Size options
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sizePanel.setBackground(Color.WHITE);

        smallRadio = new JRadioButton("S");
        mediumRadio = new JRadioButton("M (+₱10)");
        largeRadio = new JRadioButton("L (+₱20)");

        smallRadio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        smallRadio.setBackground(Color.WHITE);

        mediumRadio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mediumRadio.setBackground(Color.WHITE);

        largeRadio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        largeRadio.setBackground(Color.WHITE);

        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallRadio);
        sizeGroup.add(mediumRadio);
        sizeGroup.add(largeRadio);
        smallRadio.setSelected(true);

        sizePanel.add(smallRadio);
        sizePanel.add(mediumRadio);
        sizePanel.add(largeRadio);

        add(sizePanel);

        // Add button
        createAddButton();
    }

    @Override
    public OrderItem createOrderItem() {
        String size = "Small";
        if (mediumRadio.isSelected()) {
            size = "Medium";
        } else if (largeRadio.isSelected()) {
            size = "Large";
        }

        return new OrderItem(
                menuItem,
                (Integer) quantitySpinner.getValue(),
                size,
                false, // No fries for drinks
                false // No extra drink for drinks
        );
    }
}