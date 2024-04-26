package GUI;

import Classes.Customer;
import Classes.Order;
import Classes.Person;
import Classes.Shop;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;


import java.util.List;

public class CustomerOrdersPage extends Application {

    private Customer customer;

    @Override
    public void start(Stage primaryStage) {
        Person loggedInPerson = Shop.getLoggedInPerson();

        if (loggedInPerson instanceof Customer) {
            customer = (Customer) loggedInPerson;

            StackPane root = new StackPane();
            root.setAlignment(Pos.CENTER);

            // Display past orders
            List<Order> pastOrders = customer.getPastOrders();
            if (!pastOrders.isEmpty()) {
                VBox orderBox = new VBox(10);
                orderBox.setAlignment(Pos.CENTER);
                orderBox.setPadding(new Insets(20));

                for (Order order : pastOrders) {
                    Label orderLabel = new Label("Order ID: " + order.getOrderId());
                    orderLabel.setFont(new Font(20));
                    orderLabel.setOnMouseClicked(event -> {
                        // Navigate to OrderDetailsPage when clicked
                        primaryStage.close();
                        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(order);
                        orderDetailsPage.start(new Stage());
                    });
                    orderBox.getChildren().add(orderLabel);
                }

                // Add the orderBox to a scroll pane
                ScrollPane scrollPane = new ScrollPane(orderBox);
                scrollPane.setFitToWidth(true);
                scrollPane.setPrefHeight(400);

                root.getChildren().add(scrollPane);
            } else {
                Label noOrdersLabel = new Label("No past orders.");
                noOrdersLabel.setFont(new Font(20));
                root.getChildren().add(noOrdersLabel);
            }

            Button returnButton = new Button("Return");
            returnButton.setLayoutX(307.0);
            returnButton.setLayoutY(411.0);
            returnButton.setPrefSize(100, 40);
            returnButton.setFont(new Font(20));
            returnButton.setOnAction(event -> {
                primaryStage.close();
                ProductsPage productsPage = new ProductsPage();
                productsPage.start(new Stage());
                

            });

            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.TOP_LEFT);
            hbBtn.setPadding(new Insets(20));
            hbBtn.getChildren().addAll(returnButton);


            root.getChildren().add(hbBtn);
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Customer Orders");
            primaryStage.show();
        } else {
            System.out.println("Error: Logged-in person is not a customer.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
