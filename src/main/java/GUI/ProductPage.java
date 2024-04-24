package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import Classes.Product;
import Classes.Shop;

public class ProductPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Set background color
        root.setBackground(new Background(new BackgroundFill(Color.rgb(30, 30, 30), null, null)));

        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search products...");
        searchField.setPrefWidth(300);
        BorderPane.setAlignment(searchField, Pos.CENTER);
        root.setTop(searchField);

        // Cart button
        Button cartButton = new Button("Cart");
        cartButton.setFont(Font.font(14));
        cartButton.setPrefWidth(80);
        HBox cartBox = new HBox(cartButton);
        cartBox.setAlignment(Pos.CENTER_RIGHT);
        cartBox.setPadding(new Insets(10));
        root.setTop(cartBox);
        BorderPane.setAlignment(cartBox, Pos.CENTER_RIGHT);

        // Product grid
        GridPane productGrid = new GridPane();
        productGrid.setAlignment(Pos.CENTER);
        productGrid.setPadding(new Insets(20));
        productGrid.setHgap(20);
        productGrid.setVgap(20);

        // Add product tiles to the grid (static data)
        List<Product> products =Shop.products;
        addProductTiles(productGrid, products, primaryStage);

        // ScrollPane to enable scrolling when more than 10 products
        ScrollPane scrollPane = new ScrollPane(productGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportHeight(400);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Page");
        primaryStage.show();
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

    public ProductTile(Product product, Stage primaryStage) {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Product image
        ImageView imageView = new ImageView(new Image(product.getImageUrl()));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        // Product details
        Text productName = new Text(product.getProductName());
        productName.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        productName.setFill(Color.BLACK); // Set text color to white
        Text productDescription = new Text(product.getDescription());
        productDescription.setFont(Font.font("Arial", 12));
        productDescription.setFill(Color.BLACK); // Set text color to white
        Text productPrice = new Text("$" + product.getPrice());
        productPrice.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        productPrice.setFill(Color.BLACK); // Set text color to white

        // Add all components to the tile
        getChildren().addAll(imageView, productName, productDescription, productPrice);

        // Set action for clicking on the product tile (just an example)
        setOnMouseClicked(event -> {
            // You can implement custom logic for handling tile clicks here
            // For example, opening a detailed view of the product
            System.out.println("Clicked on product: " + product.getProductName());
        });
    }
}

