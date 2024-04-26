package GUI;

import java.util.ArrayList;

import Classes.Customer;
import Classes.Person;
import Classes.Shop;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpGUI extends Application {

    private String action;

    public SignUpGUI(String action) {
        this.action = action;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        TextField idField = new TextField();
        idField.setLayoutX(450.0);
        idField.setLayoutY(75.0);
        idField.setPromptText("ID");

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(450.0);
        passwordField.setLayoutY(111.0);
        passwordField.setPromptText("Password");

        Label titleLabel = new Label("SignUp Page");
        titleLabel.setLayoutX(215.0);
        titleLabel.setLayoutY(30.0);
        titleLabel.setPrefHeight(45.0);
        titleLabel.setPrefWidth(177.0);
        titleLabel.setStyle("-fx-background-color: black;");
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        titleLabel.setFont(new Font(25.0));

        TextField firstNameField = new TextField();
        firstNameField.setLayoutX(450.0);
        firstNameField.setLayoutY(146.0);
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setLayoutX(450.0);
        lastNameField.setLayoutY(183.0);
        lastNameField.setPromptText("Last Name");

        TextField addressField = new TextField();
        addressField.setLayoutX(450.0);
        addressField.setLayoutY(298.0);
        addressField.setPromptText("Address");

        TextField emailField = new TextField();
        emailField.setLayoutX(450.0);
        emailField.setLayoutY(369.0);
        emailField.setPromptText("Email");

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setLayoutX(190.0);
        createAccountButton.setLayoutY(200.0);
        createAccountButton.setMnemonicParsing(false);
        createAccountButton.setPrefHeight(45.0);
        createAccountButton.setPrefWidth(114.0);
        createAccountButton.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String address = addressField.getText().trim();
            String email = emailField.getText().trim();
            String userIDText = idField.getText().trim();
            String password = passwordField.getText();

            // Check if any field is empty
            if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || email.isEmpty()
                    || userIDText.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the fields.");
                alert.showAndWait();
                return; // Exit the method
            }

            // Check if userID is a valid integer
            int userID;
            try {
                userID = Integer.parseInt(userIDText);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid User ID");
                alert.setHeaderText(null);
                alert.setContentText("User ID must be a valid integer.");
                alert.showAndWait();
                return; // Exit the method
            }

            // Check if the entered ID is already taken
            for (Person existingCustomer : Shop.persons) {
                if (existingCustomer.getId() == userID) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ID Already Taken");
                    alert.setHeaderText(null);
                    alert.setContentText("The entered ID is already taken. Please choose a different one.");
                    alert.showAndWait();
                    return; // Exit the method
                }
            }

            // Create a new instance of Customer
            Customer customer = new Customer(userID, email, password, firstName, lastName, address,
                    new ArrayList<>(), 0, new ArrayList<>());

            // Add the customer to your data structure or perform any other necessary
            // actions
            Shop.persons.add(customer);
            Shop.setLoggedInPerson(customer);

            // Show success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Account Created");
            alert.setHeaderText(null);
            alert.setContentText("Your account has been created successfully.");
            
            Stage customerStage = new Stage();
            ProductsPage customerGUI = new ProductsPage();
            customerGUI.start(customerStage);
            primaryStage.close();

            // Close the current stage or navigate to another page
            primaryStage.close();
        });

        Button returnButton = new Button("Return");
        returnButton.setLayoutX(200);
        returnButton.setLayoutY(335);
        returnButton.setPrefSize(83, 45);
        returnButton.setOnAction(event -> {
            if (action.equals("create")) {
                Stage adminStage = new Stage();
                AdminGUI adminGUI = new AdminGUI();
                adminGUI.start(adminStage);
                primaryStage.close();
            } else if (action.equals("signup")) {
                Stage mainpage = new Stage();
                HomePage myhelloapp = new HomePage();
                myhelloapp.start(mainpage);
                primaryStage.close();
            }
        });

        root.getChildren().addAll(
                passwordField, idField, titleLabel, createAccountButton, firstNameField, lastNameField,
                addressField, emailField, returnButton);
        Scene scene = new Scene(root, 687, 474);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SignUp Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}