package GUI;

import Classes.Product;
import Classes.Shop;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RemoveProductsGUI extends Application {

    private TextField searchField;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Search field
        searchField = new TextField();
        searchField.setPromptText("Search products...");
        searchField.setPrefWidth(300);

        // Return button
        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            AdminGUI adminGUI = new AdminGUI();
            try {
                adminGUI.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Combine search bar and return button in one HBox
        HBox topBox = new HBox(10, new Text("Welcome!"), searchField, returnButton);
        topBox.setAlignment(Pos.CENTER_LEFT);
        root.setTop(topBox);

        // Product grid
        GridPane productGrid = new GridPane();
        productGrid.setAlignment(Pos.CENTER);
        productGrid.setPadding(new Insets(20));
        productGrid.setHgap(20);
        productGrid.setVgap(20);

        // Search functionality
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.trim().toLowerCase();
            List<Product> filteredProducts = new ArrayList<>();

            if (searchText.isEmpty()) {
                // If search text is empty, show all products
                filteredProducts.addAll(Shop.products);
            } else {
                // Filter products based on search text
                for (Product product : Shop.products) {
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

        // Initialize product grid
        addProductTiles(productGrid, Shop.products, primaryStage);

        // Scroll pane
        ScrollPane scrollPane = new ScrollPane(productGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportHeight(400);
        root.setCenter(scrollPane);

        // Set the scene
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
            ProductTile productTile = new ProductTile(product, primaryStage);
            productGrid.add(productTile, colIndex, rowIndex);

            colIndex++;
            if (colIndex == columnCount) {
                colIndex = 0;
                rowIndex++;
            }
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
            setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

            // Product image
            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(new javafx.scene.image.Image(product.getImageUrl()));
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
            Shop.products.remove(product);
            refreshProductGrid();
        }

        private void refreshProductGrid() {
            GridPane productGrid = (GridPane) getParent();
            productGrid.getChildren().clear();
            addProductTiles(productGrid, Shop.products, primaryStage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
