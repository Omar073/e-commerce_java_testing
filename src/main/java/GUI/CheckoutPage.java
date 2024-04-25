package GUI;

import Classes.Customer;
import Classes.Person;
import Classes.Shop;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CheckoutPage extends Application {
    private Customer customer;

    @Override
    public void start(Stage primaryStage) {
        Person loggedInPerson = Shop.getLoggedInPerson();
    
        if (loggedInPerson instanceof Customer) {
            // If the logged-in person is a customer, assign it to the customer variable
            customer = (Customer) loggedInPerson;
    
            primaryStage.setTitle("Checkout");
    
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));
    
            Scene scene = new Scene(grid, 800, 600);
            primaryStage.setScene(scene);
    
            // Payment information fields
            Label cardLabel = new Label("Card Number:");
            TextField cardField = new TextField();
    
            Label expiryLabel = new Label("Expiry Date:");
            TextField expiryField = new TextField();
    
            Label cvvLabel = new Label("CVV:");
            TextField cvvField = new TextField();
    
            grid.add(cardLabel, 0, 0);
            grid.add(cardField, 1, 0);
            grid.add(expiryLabel, 0, 1);
            grid.add(expiryField, 1, 1);
            grid.add(cvvLabel, 0, 2);
            grid.add(cvvField, 1, 2);
    
            // Checkout button
            Button checkoutButton = new Button("Checkout");
            checkoutButton.setFont(Font.font(14));
    
            // Return button
            Button returnButton = new Button("Return");
            returnButton.setFont(Font.font(14));
            returnButton.setOnAction(e -> {
                primaryStage.close();
                MyCartPage mycart = new MyCartPage();
                mycart.start(new Stage());
            });
    
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().addAll(checkoutButton, returnButton);
            grid.add(hbBtn, 1, 4);
    
            checkoutButton.setOnAction(event -> {
                // Validate card information
                String cardNumber = cardField.getText();
                String expiryDate = expiryField.getText();
                String cvv = cvvField.getText();
    
                if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                    // Show error message if any field is empty
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all card information fields.");
                    alert.showAndWait();
                    return; // Exit method if any field is empty
                }
    
                if (!cardNumber.matches("\\d{16}")) {
                    // Show error message if card number is invalid
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid card number. Please enter a 16-digit numeric card number.");
                    alert.showAndWait();
                    return; // Exit method if card number is invalid
                }
    
                // Validate expiry date (for simplicity, assume expiry date must be in the format MM/YY)
                if (!expiryDate.matches("\\d{2}/\\d{2}")) {
                    // Show error message if expiry date is invalid
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid expiry date. Please enter expiry date in the format MM/YY.");
                    alert.showAndWait();
                    return; // Exit method if expiry date is invalid
                }
    
                if (!cvv.matches("\\d{3}")) {
                    // Show error message if CVV is invalid
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid CVV. Please enter a 3-digit numeric CVV.");
                    alert.showAndWait();
                    return; // Exit method if CVV is invalid
                }
    
                // Process payment and create order
                boolean orderPlaced = customer.placeOrder();
    
                if (orderPlaced) {
                    // Show success message if order was successfully placed
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Payment Confirmation");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Payment processed successfully!\nOrder created.");
                    successAlert.showAndWait();

                    // Return to products Page
                    primaryStage.close();
                    ProductsPage productsPage = new ProductsPage();
                    productsPage.start(new Stage());
                } else {
                    // Show error message if order could not be placed
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("An error occurred while processing your order. Please try again later.");
                    errorAlert.showAndWait();
                }
    
    
            });
    
            primaryStage.show();
        } else {
    
            System.out.println("Error: Logged-in person is not a customer.");
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
