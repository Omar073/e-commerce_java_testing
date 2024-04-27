package GUI;

import Classes.CartItem;
import Classes.Order;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class OrderDetailsPage extends Application {

    private Order order;

    // Constructor to receive the Order object
    public OrderDetailsPage(Order order) {
        this.order = order;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);

        // Display order details
        Label orderIdLabel = new Label("Order ID: " + order.getOrderId());
        Label timestampLabel = new Label("Order Timestamp: " + order.getOrderTimestamp());
        Label subtotalLabel = new Label("Order Subtotal: $" + order.getOrderSubtotal());
        Label shippingAddressLabel = new Label("Shipping Address: " + order.getShippingAddress());

        root.getChildren().addAll(orderIdLabel, timestampLabel, subtotalLabel, shippingAddressLabel);

        // Create accordion for order items
        Accordion accordion = new Accordion();
        List<CartItem> orderItems = order.getOrderItems();
        for (CartItem item : orderItems) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(item.getProduct().getProductName());
            VBox content = new VBox();
            Label quantityLabel = new Label("Quantity: " + item.getQuantity());
            Label priceLabel = new Label("Price: $" + item.getProduct().getPrice());
            content.getChildren().addAll(quantityLabel, priceLabel);
            titledPane.setContent(content);
            accordion.getPanes().add(titledPane);
        }

        // Add accordion to scroll pane
        ScrollPane scrollPane = new ScrollPane(accordion);
        scrollPane.setFitToWidth(true);

        




     


        root.getChildren().add(scrollPane);

                // add a return button
                Button returnButton = new Button("Return"); 
                returnButton.setOnAction(e -> {
                    primaryStage.close();
        
                    CustomerOrdersPage customerOrdersPage = new CustomerOrdersPage();
                    customerOrdersPage.start(new Stage());
                });
                root.getChildren().add(returnButton);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Order Details");
        primaryStage.show();
    }

    public static void main(String[] args) {
       
        launch(args);
    }
}
