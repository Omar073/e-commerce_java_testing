package GUI;
import Classes.Customer;
import Classes.Person;
import Classes.Product;
import Classes.Shop;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SingleProductPage extends Application {
    private Product product;
    private BorderPane root;
    private Customer customer;

    public SingleProductPage(Product product) {
        this.product = product;
    }

    public void show(Stage primaryStage) {
        start(primaryStage);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        Person loggedInPerson = Shop.getLoggedInPerson();

        if (loggedInPerson instanceof Customer) {
            customer = (Customer) loggedInPerson;

        root = new BorderPane();
        root.setPadding(new Insets(10));

        // Set background color
        root.setBackground(new Background(new BackgroundFill(Color.rgb(30, 30, 30), null, null)));

        // Product image
        ImageView imageView = new ImageView(new Image(product.getImageUrl()));
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);

        // Product details
        Text productName = new Text(product.getProductName());
        productName.setFont(Font.font("Arial", 20));
        Text productDescription = new Text(product.getDescription());
        productDescription.setFont(Font.font("Arial", 16));
        Text productPrice = new Text("$" + String.format("%.2f", product.getPrice()));
        productPrice.setFont(Font.font("Arial", 18));

        // Quantity selection
        Spinner<Integer> quantitySpinner = new Spinner<>();
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

        // Button to add to cart
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setFont(Font.font("Arial", 16));
        addToCartButton.setPrefWidth(150);
        addToCartButton.setOnAction(event -> {
            int quantity = quantitySpinner.getValue();
            String addToCartMessage = customer.addToCart(product, quantity);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Add to Cart");
            alert.setHeaderText(null);
            alert.setContentText(addToCartMessage);
            alert.showAndWait();
        });

        // Button to go back
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 16));
        backButton.setPrefWidth(150);
        backButton.setOnAction(event -> {
            // Create and show the products page with the current customer
            ProductsPage productPage = new ProductsPage();
            productPage.start(new Stage());

            // Close the current window
            primaryStage.close();
        });

        // Layout for quantity selection and add to cart button
        HBox quantityBox = new HBox(20, quantitySpinner, addToCartButton);
        quantityBox.setAlignment(Pos.CENTER);

        // Layout for buttons
        HBox buttonBox = new HBox(20, quantityBox, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Layout for product details
        VBox productDetailsBox = new VBox(20, productName, productDescription, productPrice, buttonBox);
        productDetailsBox.setAlignment(Pos.CENTER);
        productDetailsBox.setPadding(new Insets(20));

        // Set components in the center of the border pane
        root.setCenter(imageView);
        root.setBottom(productDetailsBox);

        Scene scene = new Scene(root, 800, 600); // Set preferred window size
        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Details");
    }else{
        System.out.println("Error: Logged-in person is not a customer.");
    
    }
    }

    public BorderPane getRootNode() {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
