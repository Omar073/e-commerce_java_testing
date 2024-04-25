package GUI;

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

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Checkout");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 400, 300);
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
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(checkoutButton);
        grid.add(hbBtn, 1, 4);

        checkoutButton.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Payment processed successfully!\nOrder created.");
            alert.showAndWait();

            primaryStage.close();
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
