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
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

            List<Order> pastOrders = customer.getPastOrders();

            if (!pastOrders.isEmpty()) {
                TilePane orderPane = new TilePane();
                orderPane.setPadding(new Insets(20));
                orderPane.setHgap(10);
                orderPane.setVgap(10);
                orderPane.setAlignment(Pos.TOP_LEFT);

                for (Order order : pastOrders) {
                    Label orderLabel = new Label("Order ID: " + order.getOrderId());
                    orderLabel.setFont(new Font(20));
                    orderLabel.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");
                    orderLabel.setOnMouseClicked(event -> {
                        System.out.println("Order ID clicked: " + order.getOrderId());
                        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(order);
                        orderDetailsPage.start(new Stage());
                    });
                    orderPane.getChildren().add(orderLabel);
                }

                ScrollPane scrollPane = new ScrollPane(orderPane);
                scrollPane.setFitToWidth(true);
                scrollPane.setPrefViewportHeight(400);

                root.getChildren().add(scrollPane);
            } else {
                Label noOrdersLabel = new Label("No past orders.");
                noOrdersLabel.setFont(new Font(20));
                root.getChildren().add(noOrdersLabel);
            }

            Button returnButton = new Button("Return");
            returnButton.setPrefSize(100, 40);
            returnButton.setFont(new Font(20));
            returnButton.setOnAction(event -> {
                primaryStage.close();
                ProductsPage productsPage = new ProductsPage();
                productsPage.start(new Stage());
            });

            StackPane.setAlignment(returnButton, Pos.BOTTOM_LEFT);
            root.getChildren().add(returnButton);

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
