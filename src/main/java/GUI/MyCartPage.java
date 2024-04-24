package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyCartPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Create a VBox to hold the products in the cart
        VBox cartItems = new VBox();
        cartItems.setSpacing(10);
        cartItems.setPadding(new Insets(10));

        // Add sample products to the cart (replace with actual products from user's cart)
        addSampleProductsToCart(cartItems);

        // Scroll pane for the cart items
        ScrollPane scrollPane = new ScrollPane(cartItems);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);

        // Total order value label
        Label totalLabel = new Label("Total: $100.00"); // Sample total value
        totalLabel.setFont(Font.font(18));

        // Checkout button
        Button checkoutButton = new Button("Checkout");
        checkoutButton.setFont(Font.font(16));
        checkoutButton.setPrefWidth(150);
        checkoutButton.setOnAction(event -> {
            // Add logic to process the checkout
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Checkout");
            alert.setHeaderText(null);
            alert.setContentText("Checkout completed!");
            alert.showAndWait();
        });

        // Bottom layout for total value and checkout button
        VBox bottomLayout = new VBox(10, totalLabel, checkoutButton);
        bottomLayout.setAlignment(Pos.CENTER_RIGHT);

        // Set components in the center and bottom of the border pane
        root.setCenter(scrollPane);
        root.setBottom(bottomLayout);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Cart");
        primaryStage.show();
    }

    private void addSampleProductsToCart(VBox cartItems) {
        // Sample products in the cart (replace with actual products from user's cart)
        for (int i = 1; i <= 10; i++) {
            Label productLabel = new Label("Product " + i); // Sample product name
            productLabel.setFont(Font.font(16));
            cartItems.getChildren().add(productLabel);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
