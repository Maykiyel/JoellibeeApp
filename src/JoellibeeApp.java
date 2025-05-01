import javax.swing.*;
import java.awt.*;
import model.OrderModel;
import view.MainView;
import controller.OrderController;

public class JoellibeeApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize the model, view, and controller
            OrderModel model = new OrderModel();
            MainView view = new MainView();
            OrderController controller = new OrderController(model, view);

            // Display the application
            view.setVisible(true);
        });
    }
}