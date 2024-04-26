package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import Classes.Customer;
import Classes.Person;
import Classes.Product;
import Classes.Shop;

public class ProductsPage extends Application {

    // List to hold all products
    private List<Product> allProducts;

    private Customer customer;

    @Override
    public void start(Stage primaryStage) {
        Person loggedInPerson = Shop.getLoggedInPerson();

        if (loggedInPerson instanceof Customer) {
            customer = (Customer) loggedInPerson;

            BorderPane root = new BorderPane();
            root.setPadding(new Insets(10));

            // Set background color
            root.setBackground(new Background(new BackgroundFill(Color.rgb(30, 30, 30), null, null)));

            // Search bar
            TextField searchField = new TextField();
            searchField.setPromptText("Search products...");
            searchField.setPrefWidth(300);

            // Cart button
            Button cartButton = new Button("Cart");
            cartButton.setFont(Font.font(14));
            cartButton.setPrefWidth(80);
            cartButton.setOnAction(event -> {
                MyCartPage myCartPage = new MyCartPage();
                myCartPage.start(primaryStage);
            });

            Label welcomeLabel = new Label("Welcome, " + customer.getFirstName() + "!");
            welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            welcomeLabel.setTextFill(Color.WHITE);
            welcomeLabel.setAlignment(Pos.CENTER);

            // Inside the start method of ProductsPage class

            Button logoutButton = new Button("Logout");
            logoutButton.setFont(Font.font(14));
            logoutButton.setPrefWidth(80);
            logoutButton.setOnAction(event -> {
                // Logout functionality
                Shop.setLoggedInPerson(null);
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

            Button myOrders = new Button("My Orders");
            myOrders.setFont(Font.font(14));
            myOrders.setPrefWidth(100);
            myOrders.setOnAction(event -> {
                primaryStage.close(); // Close current window
                CustomerOrdersPage orders = new CustomerOrdersPage();
                orders.start(new Stage()); // Open login screen
            });

            HBox searchBarBox = new HBox(13, welcomeLabel, searchField, cartButton, myOrders);
            searchBarBox.setAlignment(Pos.CENTER_LEFT);
            root.setTop(searchBarBox);

            // Add logout button to the top-right corner
            HBox topRightBox = new HBox(logoutButton);
            topRightBox.setAlignment(Pos.CENTER_RIGHT);
            searchBarBox.getChildren().add(topRightBox);

            GridPane productGrid = new GridPane();
            productGrid.setAlignment(Pos.CENTER);
            productGrid.setPadding(new Insets(20));
            productGrid.setHgap(20);
            productGrid.setVgap(20);

            allProducts = Shop.products;

            addProductTiles(productGrid, allProducts, primaryStage);

            // ScrollPane to enable scrolling when more than 10 products
            ScrollPane scrollPane = new ScrollPane(productGrid);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.setPrefViewportHeight(400);
            root.setCenter(scrollPane);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                String searchText = newValue.trim().toLowerCase();
                List<Product> filteredProducts = new ArrayList<>();

                if (searchText.isEmpty()) {
                    // If search text is empty, show all products
                    filteredProducts.addAll(allProducts);
                } else {
                    // Filter products based on search text
                    for (Product product : allProducts) {
                        if (product.getProductName().toLowerCase().contains(searchText) ||
                                product.getDescription().toLowerCase().contains(searchText)) {
                            filteredProducts.add(product);
                        }
                    }
                }

                // Clear existing product grid and add filtered products
                productGrid.getChildren().clear();
                addProductTiles(productGrid, filteredProducts, primaryStage);
            });

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Product Page");
            primaryStage.show();
        } else {

            System.out.println("Error: Logged-in person is not a customer.");
        }
    }

    private void addProductTiles(GridPane productGrid, List<Product> products, Stage primaryStage) {
        int columnCount = 3;
        int rowIndex = 0;
        int colIndex = 0;

        for (Product product : products) {
            // Create product tile
            ProductTile productTile = new ProductTile(product, primaryStage);
            productGrid.add(productTile, colIndex, rowIndex);

            // Adjust column and row indices
            colIndex++;
            if (colIndex == columnCount) {
                colIndex = 0;
                rowIndex++;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class ProductTile extends VBox {
    private Product product;
    private Stage primaryStage;

    public ProductTile(Product product, Stage primaryStage) {
        this.product = product;
        this.primaryStage = primaryStage;

        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
        setPadding(new Insets(10));
        setStyle(
                "-fx-background-color: #f5f5f5; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Product image
        ImageView imageView = new ImageView(new Image(product.getImageUrl()));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        // Product details
        VBox detailsBox = new VBox(5);
        Text productName = new Text(product.getProductName());
        productName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text productDescription = new Text(product.getDescription());
        productDescription.setFont(Font.font("Arial", 12));
        Text productPrice = new Text("$" + String.format("%.2f", product.getPrice()));
        productPrice.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        detailsBox.getChildren().addAll(productName, productDescription, productPrice);

        // Add image and details to the tile
        getChildren().addAll(imageView, detailsBox);

        // Set action for clicking on the product tile
        setOnMouseClicked(this::handleTileClick);
    }

    private void handleTileClick(MouseEvent event) {
        // Navigate to single product page when clicked
        SingleProductPage singleProductPage = new SingleProductPage(product);
        singleProductPage.start(primaryStage);
    }
}
