package GUI;

import Classes.CartItem;
import Classes.Customer;
import Classes.Person;
import Classes.Product;
import Classes.Shop;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class MyCartPage extends Application {
    private Customer customer;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        Person loggedInPerson = Shop.getLoggedInPerson();

        if (loggedInPerson instanceof Customer) {
            // If the logged-in person is a customer, assign it to the customer variable
            customer = (Customer) loggedInPerson;

            // Back button
            Button backButton = new Button("Back to Products");
            backButton.setOnAction(event -> {
                primaryStage.close();
                ProductsPage productsPage = new ProductsPage();
                productsPage.start(new Stage());
            });

            VBox cartItems = new VBox();
            cartItems.setSpacing(10);
            cartItems.setPadding(new Insets(10));

            // Get the cart items from the customer
            List<CartItem> cart = customer.getCart();

            // Iterate through the cart items and display them
            for (CartItem item : cart) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
            
                Label itemLabel = new Label(product.getProductName() + " - Quantity: " + quantity);
                ImageView productImageView = new ImageView(product.getImageUrl());
                productImageView.setFitHeight(100); // Adjust height as needed
                productImageView.setFitWidth(100); // Adjust width as needed
            
                Button removeButton = new Button("Remove");
                removeButton.setOnAction(event -> {
                    // Remove the product from the cart
                    customer.removeFromCart(item);
                    // Refresh the cart page
                    primaryStage.close();
                    MyCartPage myCartPage = new MyCartPage();
                    myCartPage.start(new Stage());
                });
            
                Button increaseButton = new Button("+");
                increaseButton.setOnAction(event -> {
                    String result = customer.increaseQuantity(item);
                    updateCartDisplay(primaryStage, root);
                    showAlert(result);
                });
            
                Button decreaseButton = new Button("-");
                decreaseButton.setOnAction(event -> {
                    String result = customer.decreaseQuantity(item);
                    updateCartDisplay(primaryStage, root);
                    showAlert(result);
                });
            
                HBox quantityControl = new HBox(5, increaseButton, decreaseButton);
            
                // VBox to contain item details
                VBox itemDetails = new VBox(5);
                itemDetails.getChildren().addAll(itemLabel, quantityControl);
            
                // Horizontal box to contain item image and details
                HBox itemBox = new HBox(10);
                itemBox.getChildren().addAll(productImageView, itemDetails, removeButton);
                cartItems.getChildren().add(itemBox);
            }
            

            // Scroll pane for the cart items
            ScrollPane scrollPane = new ScrollPane(cartItems);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefHeight(300);

            // Total order value label
            Label totalLabel = new Label("Total: $" + calculateTotal(cart)); // Calculate total value
            totalLabel.setFont(Font.font(18));

            // Checkout button
            Button checkoutButton = new Button("Checkout");
            checkoutButton.setFont(Font.font(16));
            checkoutButton.setPrefWidth(150);
            checkoutButton.setOnAction(event -> {
                primaryStage.close();
                if (cart.isEmpty()) {
                    showAlert("Cart is empty. Please add items to the cart before checking out.");
                    return;
                }else {
                CheckoutPage checkoutPage = new CheckoutPage();
                checkoutPage.start(new Stage());
                }
            });

            // Clear cart button
            Button clearButton = new Button("Clear Cart");
            clearButton.setOnAction(event -> {
                // Clear the cart
                cart.clear();
                // Refresh the cart page
                primaryStage.close();
                MyCartPage myCartPage = new MyCartPage();
                myCartPage.start(new Stage());
            });

            // Bottom layout for total value, checkout button, and clear cart button
            VBox bottomLayout = new VBox(10, totalLabel, checkoutButton, clearButton);
            bottomLayout.setAlignment(Pos.CENTER_RIGHT);

            // Set components in the center and bottom of the border pane
            root.setTop(backButton);
            root.setCenter(scrollPane);
            root.setBottom(bottomLayout);

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Cart");
            primaryStage.show();
        } else {
            System.out.println("Error: Logged-in person is not a customer.");
        }
    }

    private void updateCartDisplay(Stage primaryStage, BorderPane root) {
        primaryStage.close();
        MyCartPage myCartPage = new MyCartPage();
        myCartPage.start(new Stage());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Calculate the total value of items in the cart
    private double calculateTotal(List<CartItem> cart) {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
