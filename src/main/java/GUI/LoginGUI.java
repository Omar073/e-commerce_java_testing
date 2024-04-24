package GUI;

import java.util.ArrayList;


import Classes.Admin;
import Classes.Customer;
import Classes.Person;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Classes.Shop;

public class LoginGUI extends Application {



    private String enteredPassword;
    private String enteredID;

    @Override
    public void start(Stage primaryStage) {
        // Pane root = new Pane();
        VBox root = new VBox(10); // VBox with spacing of 10 between elements
        root.setAlignment(Pos.CENTER);

        Label loginLabel = new Label("Login Page");
        loginLabel.setLayoutX(247.0);
        loginLabel.setLayoutY(14.0);
        loginLabel.setPrefSize(177.0, 45.0);
        // loginLabel.setStyle("-fx-background-color: black;");
        loginLabel.setTextFill(Color.WHITE);
        loginLabel.setAlignment(javafx.geometry.Pos.CENTER);
        loginLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        loginLabel.setFont(Font.font("System", FontWeight.BOLD, 30.0));
        loginLabel.setEffect(new ColorAdjust());

        TextField idField = new TextField();
        idField.setLayoutX(250.0);
        idField.setLayoutY(107.0);
        idField.setPromptText("ID");

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(250.0);
        passwordField.setLayoutY(163.0);
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setLayoutX(250.0);
        loginButton.setLayoutY(246.0);
        loginButton.setPrefSize(80, 40);
        loginButton.setFont(new Font(18));
        loginButton.setOnAction(event -> {
            enteredPassword = passwordField.getText();
            enteredID = idField.getText();
            if(enteredID.isEmpty() || enteredPassword.isEmpty()){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid ID and password.");
                alert.showAndWait();
                return;
            }
            try {
                int userID = Integer.parseInt(enteredID);
                validateCredentials(userID, enteredPassword, Shop.persons, primaryStage);
            } catch (NumberFormatException e) {
                // Handle case where entered ID is not a valid integer
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid numeric ID.");
                alert.showAndWait();
            }
        });
        

        Button signupButton = new Button("Signup");
        loginButton.setLayoutX(350.0);
        loginButton.setLayoutY(246.0);
        signupButton.setPrefSize(80, 40);
        signupButton.setFont(new Font(18));
        signupButton.setOnAction(event -> {
            Stage signupStage = new Stage();
            SignUpGUI signupGUI = new SignUpGUI("signup");
            signupGUI.start(signupStage);
            primaryStage.close();
        });

        Button BackButton = new Button("Back");
        BackButton.setLayoutX(307.0);
        BackButton.setLayoutY(411.0);
        BackButton.setPrefSize(100, 40);
        BackButton.setFont(new Font(20));
        BackButton.setOnAction(event -> {
            Stage mainpage = new Stage();
            HomePage myhelloapp = new HomePage();
            myhelloapp.start(mainpage);
            primaryStage.close();
        });

        // VBox vbox = new VBox(10); // Add spacing of 10 between elements
        // vbox.setAlignment(Pos.CENTER);
        // vbox.getChildren().addAll(loginLabel, idField, passwordField, loginButton);
        
        // HBox hbox = new HBox(10); // Add spacing of 10 between elements
        // hbox.setAlignment(Pos.CENTER);
        // hbox.getChildren().addAll(loginLabel, idField, passwordField, loginButton);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        // gridPane.setPadding(new Insets(100));
        gridPane.setMinSize(500, 300);
        gridPane.setHgap(25);
        gridPane.setVgap(10);
        gridPane.add(loginLabel, 0, 0, 2, 1); // Spanning 2 columns
        gridPane.add(idField, 0, 1, 2, 1); // Spanning 2 columns
        gridPane.add(passwordField, 0, 2, 2, 1); // Spanning 2 columns
        gridPane.add(loginButton, 0, 3, 2, 1); // col index, row index, col span, row span
        gridPane.add(signupButton, 1, 3, 2, 1); // Spanning 2 columns
        gridPane.add(BackButton, 0, 18, 2, 1); // Spanning 2 columns
        GridPane.setMargin(loginLabel, new Insets(0, 0, 15, 0)); // top, right, bottom, left
        GridPane.setMargin(loginButton, new Insets(5, 25, 0, 0)); // top, right, bottom, left
        GridPane.setMargin(signupButton, new Insets(5, 0, 0, 70)); // top, right, bottom, left
        GridPane.setMargin(BackButton, new Insets(5, 0, 0, 45)); // top, right, bottom, left


        // // Center the GridPane inside the StackPane
        // StackPane stackPane = new StackPane(gridPane);
        // stackPane.setAlignment(Pos.CENTER);
        // stackPane.setPadding(new Insets(10));
        
        // // Center the StackPane inside the window
        // stackPane.setLayoutX((primaryStage.getWidth() - stackPane.getBoundsInParent().getWidth()) / 2);
        // stackPane.setLayoutY((primaryStage.getHeight() - stackPane.getBoundsInParent().getHeight()) / 2);

        root.getChildren().add(gridPane);

        // Set panel size
        double panelWidth = 687;
        double panelHeight = 474;
        root.setMinSize(panelWidth, panelHeight);
        root.setMaxSize(panelWidth, panelHeight);

        // Set background image
        // Image backgroundImage = new Image("image.jpg");
        // BackgroundImage background = new BackgroundImage(
        //     backgroundImage,
        //     BackgroundRepeat.NO_REPEAT,
        //     BackgroundRepeat.NO_REPEAT,
        //     BackgroundPosition.CENTER,
        //     new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        // );
        // root.setBackground(new Background(background));

        Scene scene = new Scene(root, panelWidth, panelHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    private boolean validateCredentials(int userID, String password, ArrayList<Person> persons, Stage primaryStage) {
        
        boolean isValidID = false;
        boolean isValidPassword = false; 
        for (Person person : persons) {
            if (person.getId() == userID && person.getPassword().equalsIgnoreCase(password) && person instanceof Admin) {
                isValidID = true;
                isValidPassword = true;
                showSuccessAlert(person.getFirstName());
                Stage adminStage = new Stage();
                ProductsPage adminGUI = new ProductsPage();
                adminGUI.start(adminStage);
                primaryStage.close();
            } 
            else if (person.getId() == userID && person.getPassword().equalsIgnoreCase(password) && person instanceof Admin == false) {
                isValidID = true;
                isValidPassword = true;
                showSuccessAlert(person.getFirstName());
                Stage readerStage = new Stage();
                // ReaderGUI readerGUI = new ReaderGUI(person);
                // readerGUI.start(readerStage);
                primaryStage.close();
            }
        }

        if (isValidID == false || isValidPassword == false) {
            showErrorAlert(primaryStage);
            Stage loginStage = new Stage();
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.start(loginStage);
            primaryStage.close();
            return false;
        } else {
            return true;
        }
    }


    

    private void showSuccessAlert(String name) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText(null);
        alert.setContentText("Welcome " + name + " !");
        alert.showAndWait();
    }

    private void showErrorAlert(Stage primaryStage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText(null);
        alert.setContentText("Invalid credentials. Please enter a valid ID and password.");

        ButtonType logoutButton = new ButtonType("Logout", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().add(logoutButton);

        Button logoutButtonControl = (Button) alert.getDialogPane().lookupButton(logoutButton);
        logoutButtonControl.setOnAction(event -> {
            System.exit(0);
        });

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}