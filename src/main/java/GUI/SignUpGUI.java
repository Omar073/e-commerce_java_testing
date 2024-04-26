package GUI;

import java.util.ArrayList;

import Classes.Customer;
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

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(450.0);
        passwordField.setLayoutY(111.0);
        passwordField.setPromptText("Password");

        TextField idField = new TextField();
        idField.setLayoutX(450.0);
        idField.setLayoutY(75.0);
        idField.setPromptText("ID");

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
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            int userID = Integer.parseInt(idField.getText());
            String password = passwordField.getText();

            // Create a new instance of Reader
            Customer reader = new Customer(userID, email, password, firstName, lastName, address,
                    new ArrayList<>(), 0, new ArrayList<>());

            // Add the reader to Shop.persons
            // Shop.persons.add(reader); //TODO: for some reason the file isn't reading
            // shop.persons

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome to the Library Management System " + firstName + " !");
            alert.showAndWait();

            Stage readerStage = new Stage();
            // ReaderGUI readergui = new ReaderGUI(reader);
            // readergui.start(readerStage);
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