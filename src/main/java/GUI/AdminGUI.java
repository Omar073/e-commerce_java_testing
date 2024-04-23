package GUI;

import Classes.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdminGUI extends Application{
    
    static private Person person = null;

    //constructor
    public AdminGUI(Person person) {
        this.person = person;
    }

    //default constructor
    public AdminGUI() {}

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        Label adminLabel = new Label("Admin Page");
        adminLabel.setLayoutX(247.0);
        adminLabel.setLayoutY(14.0);
        adminLabel.setPrefSize(177.0, 45.0);
        // adminLabel.setStyle("-fx-background-color: black;");
        adminLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        adminLabel.setAlignment(javafx.geometry.Pos.CENTER);
        adminLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        adminLabel.setFont(Font.font("System", FontWeight.BOLD, 30.0));
        adminLabel.setEffect(new ColorAdjust());

        Button addBookButton = new Button("Add new book");
        addBookButton.setLayoutX(72.0);
        addBookButton.setLayoutY(91.0);
        addBookButton.setOnAction(event -> {
            // NewBook newbook = new NewBook("add");
            // Stage newbooksstage = new Stage();
            // newbook.start(newbooksstage);
            primaryStage.close();
        });

        Button removeBookButton = new Button("Remove a book");
        removeBookButton.setLayoutX(286.0);
        removeBookButton.setLayoutY(91.0);
        removeBookButton.setOnAction(event -> {
            // SearchBooksGUI searchbooksGUI = new SearchBooksGUI("admin", "delete");
            // Stage searchbooksstage = new Stage();
            // searchbooksGUI.start(searchbooksstage);
            primaryStage.close();
        });

        // Button updateBookButton = new Button("Update a book");
        // updateBookButton.setLayoutX(498.0);
        // updateBookButton.setLayoutY(91.0);
        // updateBookButton.setOnAction(event -> {
        //     SearchBooksGUI searchbooksGUI = new SearchBooksGUI("admin", "search");
        //     Stage searchbooksstage = new Stage();
        //     searchbooksGUI.start(searchbooksstage);
        //     primaryStage.close();
        // });

        Button addUserButton = new Button("Add new user");
        addUserButton.setLayoutX(74.0);
        addUserButton.setLayoutY(157.0);
        addUserButton.setOnAction(event -> {
            Stage signupstage = new Stage();
            SignUpGUI signupgui = new SignUpGUI("create");
            signupgui.start(signupstage);
            primaryStage.close();
        });

        Button searchUsersButton = new Button("Search users");
        searchUsersButton.setLayoutX(504.0);
        searchUsersButton.setLayoutY(225.0);
        searchUsersButton.setOnAction(event -> {
            // SearchUsersGui searchusersGUI = new SearchUsersGui("admin", "search");
            // Stage searchusersstage = new Stage();
            // searchusersGUI.start(searchusersstage);
            primaryStage.close();
        });

        Button removeUserButton = new Button("Remove a user");
        removeUserButton.setLayoutX(288.0);
        removeUserButton.setLayoutY(157.0);
        removeUserButton.setOnAction(event -> {
            // SearchUsersGui searchusersGUI = new SearchUsersGui("admin", "delete");
            // Stage searchusersstage = new Stage();
            // searchusersGUI.start(searchusersstage);
            primaryStage.close();
        });
        
        Button blockUserButton = new Button("Block user");
        blockUserButton.setLayoutX(510.0);
        blockUserButton.setLayoutY(340.0);
        blockUserButton.setOnAction(event -> {
            // SearchUsersGui searchusersGUI = new SearchUsersGui("admin", "block");
            // Stage searchusersstage = new Stage();
            // searchusersGUI.start(searchusersstage);
            primaryStage.close();
        });

        // Button updateUserButton = new Button("Update a user");
        // updateUserButton.setLayoutX(500.0);
        // updateUserButton.setLayoutY(157.0);

        Button viewBooksButton = new Button("View all books");
        viewBooksButton.setLayoutX(73.0);
        viewBooksButton.setLayoutY(225.0);
        viewBooksButton.setOnAction(event -> {
            // DisplayListGUI displayListGUI = new DisplayListGUI(Library.books, "admin");
            // Stage displaybooksstage = new Stage();
            // displayListGUI.start(displaybooksstage);
            primaryStage.close();
        });
        // displayListGUI.launch(args);

        Button viewUsersButton = new Button("View all users");
        viewUsersButton.setLayoutX(291.0);
        viewUsersButton.setLayoutY(225.0);
        viewUsersButton.setOnAction(event -> {
            // DisplayListGUI displayListGUI = new DisplayListGUI(Library.persons, "admin");
            // Stage displayusersstage = new Stage();
            // displayListGUI.start(displayusersstage);
            primaryStage.close();
        });


        Button searchBooksButton = new Button("Search books");
        searchBooksButton.setLayoutX(75.0);
        searchBooksButton.setLayoutY(284.0);
        searchBooksButton.setOnAction(event -> {
            // SearchBooksGUI searchbooksGUI = new SearchBooksGUI("admin", "search");
            // Stage searchbooksstage = new Stage();
            // searchbooksGUI.start(searchbooksstage);
            primaryStage.close();
        });

        Button viewCartButton = new Button("View cart");
        viewCartButton.setLayoutX(302.0);
        viewCartButton.setLayoutY(284.0);
        viewCartButton.setOnAction(event -> {
            // DisplayListGUI displayListGUI = new DisplayListGUI(person.getCart(), "admin");
            // Stage displaybooksstage = new Stage();
            // displayListGUI.start(displaybooksstage);
            primaryStage.close();
        });

        Button addToCartButton = new Button("Add to cart");
        addToCartButton.setLayoutX(507.0);
        addToCartButton.setLayoutY(284.0);
        addToCartButton.setOnAction(event -> {
            // SearchBooksGUI searchbooksGUI = new SearchBooksGUI("admin", "addtocart", person);
            // Stage searchbooksstage = new Stage();
            // searchbooksGUI.start(searchbooksstage);
            primaryStage.close();
        });

        // Button removeBookFromCartButton = new Button("Remove book from cart");
        // removeBookFromCartButton.setLayoutX(72.0);
        // removeBookFromCartButton.setLayoutY(340.0);

        Button logoutButton = new Button("Logout");
        logoutButton.setLayoutX(307.0);
        logoutButton.setLayoutY(411.0);
        logoutButton.setOnAction(event -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Logout");
            alert.setHeaderText(null);
            // alert.setContentText("Welcome to the Library Management System!");
            alert.setContentText("Logged out successfully!");
            alert.showAndWait();
            // System.exit(0);
            HomePage homepage = new HomePage();
            Stage homePageStage = new Stage();
            homepage.start(homePageStage);
            primaryStage.close();
        });

        // Set background image
        // Image backgroundImage = new Image("image.jpg");
        // BackgroundImage background = new BackgroundImage(
        //         backgroundImage,
        //         BackgroundRepeat.NO_REPEAT,
        //         BackgroundRepeat.NO_REPEAT,
        //         BackgroundPosition.CENTER,
        //         new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        // );
        // root.setBackground(new Background(background));

        root.getChildren().addAll(adminLabel, addBookButton, removeBookButton, addUserButton,
                removeUserButton, viewBooksButton, viewUsersButton, searchUsersButton,
                searchBooksButton, viewCartButton, addToCartButton, blockUserButton,
                logoutButton);

        Scene scene = new Scene(root, 687, 474);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}