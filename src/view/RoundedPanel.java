package view;

import javax.swing.*;
import java.awt.*;

/**
 * Custom panel with rounded corners and a subtle shadow effect
 */
public class RoundedPanel extends JPanel {
    private final int cornerRadius = 15;

    public RoundedPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, cornerRadius, cornerRadius);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, cornerRadius, cornerRadius);
        g2.dispose();
        super.paintComponent(g);
    }
}