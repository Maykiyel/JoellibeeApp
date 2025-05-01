// Main view class
package view;

import model.OrderItem;
import model.OrderModel;
import model.CurrencyFormatter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {
    // Menu panels
    private final List<ItemPanel> itemPanels = new ArrayList<>();

    // Cart components
    private JTextArea cartArea;
    private JLabel totalLabel;
    private JLabel itemCountLabel;

    // Order type components
    private ButtonGroup orderTypeGroup;
    private JRadioButton dineInRadio;
    private JRadioButton takeOutRadio;
    private JRadioButton deliveryRadio;

    // Action buttons
    private JButton cancelButton;
    private JButton checkoutButton;

    // Delivery panel
    private JPanel deliveryPanel;
    private JTextField addressField;
    private JTextField phoneField;

    public MainView() {
        setTitle("Joellibee Fast Food Ordering System");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupComponents();
    }

    private void setupComponents() {
        // Header panel
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Menu panel (center)
        add(createMenuPanel(), BorderLayout.CENTER);

        // Cart panel (east)
        add(createCartPanel(), BorderLayout.EAST);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 0, 18));

        JLabel logo = new JLabel("Joellibee");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        logo.setForeground(Color.WHITE);
        panel.add(logo);

        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 3, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        // These will be populated by the controller
        return panel;
    }

    public void addItemPanel(ItemPanel itemPanel) {
        JPanel menuPanel = (JPanel) getContentPane().getComponent(1);
        menuPanel.add(itemPanel);
        itemPanels.add(itemPanel);
    }

    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new TitledBorder("Your Order"));

        // Order type panel
        JPanel orderTypePanel = new JPanel();
        orderTypePanel.setBackground(Color.WHITE);

        dineInRadio = new JRadioButton("Dine-in");
        takeOutRadio = new JRadioButton("Take-out");
        deliveryRadio = new JRadioButton("Delivery");

        dineInRadio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        takeOutRadio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deliveryRadio.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        dineInRadio.setBackground(Color.WHITE);
        takeOutRadio.setBackground(Color.WHITE);
        deliveryRadio.setBackground(Color.WHITE);

        orderTypeGroup = new ButtonGroup();
        orderTypeGroup.add(dineInRadio);
        orderTypeGroup.add(takeOutRadio);
        orderTypeGroup.add(deliveryRadio);
        dineInRadio.setSelected(true);

        orderTypePanel.add(dineInRadio);
        orderTypePanel.add(takeOutRadio);
        orderTypePanel.add(deliveryRadio);

        // Delivery details panel
        deliveryPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        deliveryPanel.setBackground(Color.WHITE);
        deliveryPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        deliveryPanel.setVisible(false);

        deliveryPanel.add(new JLabel("Address:"));
        addressField = new JTextField(20);
        deliveryPanel.add(addressField);

        deliveryPanel.add(new JLabel("Contact:"));
        phoneField = new JTextField(10);
        deliveryPanel.add(phoneField);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(orderTypePanel, BorderLayout.NORTH);
        topPanel.add(deliveryPanel, BorderLayout.CENTER);

        // Item count label
        itemCountLabel = new JLabel("Items: 0");
        itemCountLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        topPanel.add(itemCountLabel, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);

        // Cart area
        cartArea = new JTextArea(15, 25);
        cartArea.setEditable(false);
        cartArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(cartArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Total label
        totalLabel = new JLabel("Total: ₱0");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(totalLabel, BorderLayout.SOUTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        cancelButton = new JButton("CANCEL");
        checkoutButton = new JButton("CHECKOUT");

        styleButton(cancelButton);
        styleButton(checkoutButton);

        buttonPanel.add(cancelButton);
        buttonPanel.add(checkoutButton);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        return panel;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(new Color(230, 0, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    // Update the cart display
    public void updateCart(List<OrderItem> items, int total) {
        cartArea.setText("");
        for (OrderItem item : items) {
            cartArea.append(item.toString() + "\n");
        }

        totalLabel.setText("Total: " + CurrencyFormatter.format(total));
        itemCountLabel.setText("Items: " + items.size());
    }

    // Set action listeners for buttons
    public void setOrderTypeListener(ActionListener listener) {
        dineInRadio.addActionListener(listener);
        takeOutRadio.addActionListener(listener);
        deliveryRadio.addActionListener(listener);
    }

    public void setCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public void setCheckoutButtonListener(ActionListener listener) {
        checkoutButton.addActionListener(listener);
    }

    // Show/hide delivery panel
    public void showDeliveryFields(boolean show) {
        deliveryPanel.setVisible(show);
        revalidate();
        repaint();
    }

    // Get order type selection
    public OrderModel.OrderType getSelectedOrderType() {
        if (dineInRadio.isSelected()) {
            return OrderModel.OrderType.DINE_IN;
        } else if (takeOutRadio.isSelected()) {
            return OrderModel.OrderType.TAKE_OUT;
        } else {
            return OrderModel.OrderType.DELIVERY;
        }
    }

    // Get delivery information
    public String getDeliveryAddress() {
        return addressField.getText();
    }

    public String getContactNumber() {
        return phoneField.getText();
    }

    // Display messages
    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
    }
}