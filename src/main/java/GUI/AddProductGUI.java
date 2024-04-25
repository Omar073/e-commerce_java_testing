package GUI;

import java.io.File;
import java.util.Optional;

import Classes.Product;
import Classes.Shop;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddProductGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create labels
        Label productNameLabel = new Label("Product Name:");
        Label descriptionLabel = new Label("Description:");
        Label categoryLabel = new Label("Category:");
        Label colorLabel = new Label("Color:");
        Label quantityLabel = new Label("Quantity:");
        Label priceLabel = new Label("Price:");
        // Label imageUrlLabel = new Label("Image URL:");

        // Create text fields
        TextField productNameField = new TextField();
        TextField descriptionField = new TextField();
        TextField categoryField = new TextField();
        TextField colorField = new TextField();
        TextField quantityField = new TextField();
        TextField priceField = new TextField();
        // TextField imageUrlField = new TextField();

        // Create a button
        Button addButton = new Button("Add Product");
        Button returnButton = new Button("Return");

        // Create a grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Add labels and text fields to the grid pane
        gridPane.add(productNameLabel, 0, 0);
        gridPane.add(productNameField, 1, 0);
        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionField, 1, 1);
        gridPane.add(categoryLabel, 0, 2);
        gridPane.add(categoryField, 1, 2);
        gridPane.add(colorLabel, 0, 3);
        gridPane.add(colorField, 1, 3);
        gridPane.add(quantityLabel, 0, 4);
        gridPane.add(quantityField, 1, 4);
        gridPane.add(priceLabel, 0, 5);
        gridPane.add(priceField, 1, 5);
        // gridPane.add(imageUrlLabel, 0, 6);
        // gridPane.add(imageUrlField, 1, 6);
        gridPane.add(addButton, 1, 7);
        gridPane.add(returnButton, 2, 7);

        // Inside your addButton.setOnAction method
        addButton.setOnAction(event -> {
            try {
                // Get values from text fields
                String productName = productNameField.getText();
                String description = descriptionField.getText();
                String category = categoryField.getText();
                String color = colorField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
        
                // Check if quantity or price is negative
                if (quantity < 0 || price < 0) {
                    // Show an alert for negative quantity or price
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Quantity and price cannot be negative!");
                    alert.showAndWait();
                } else {
                    // Proceed with adding the product
                    // Create a dialog to display the image filenames
                    ChoiceDialog<String> dialog = new ChoiceDialog<>();
                    dialog.setTitle("Select Image");
                    dialog.setHeaderText("Select an image file:");
                    dialog.setContentText("Image Files:");
        
                    // Retrieve the list of image filenames from the Images folder
                    File imagesFolder = new File("src/main/resources/Images");
                    // File imagesFolder = new File("M:/Lectures/Third year/Second term/Software Testing, Validation & Verification/e-commerce_java_testing/src/main/java/Images");
                    File[] imageFiles = imagesFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg"));
                    if (imageFiles != null) {
                        for (File file : imageFiles) {
                            dialog.getItems().add(file.getName());
                        }
                    }
        
                    // Show the dialog and wait for user input
                    Optional<String> result = dialog.showAndWait();
        
                    // Check if the user selected an image file
                    if (result.isPresent()) {
                        // Construct the full path to the selected image file
                        String selectedFilename = result.get();
                        String imageUrl = "Images/" + selectedFilename; // Assuming the Images folder is located in the project root
        
                        // Create a new Product object with the entered values
                        Product newProduct = new Product(null, productName, description, category, color, quantity, imageUrl, price);
                        Shop.products.add(newProduct);
        
                        // Show an alert indicating that the new product has been added successfully
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Product Added");
                        alert.setHeaderText(null);
                        alert.setContentText("New product added successfully!");
                        alert.showAndWait();
        
                        // Close the current window and open the AdminGUI window
                        Stage stage = (Stage) addButton.getScene().getWindow();
                        stage.close();
        
                        // Open the AdminGUI window
                        AdminGUI adminGUI = new AdminGUI();
                        adminGUI.start(new Stage());
                    } else {
                        // If no image file is selected, display an error message
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("No image selected!");
                        alert.showAndWait();
                    }
                }
            } catch (NumberFormatException e) {
                // Show an alert for invalid quantity or price format
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid quantity or price format!");
                alert.showAndWait();
            }
        });
        

        // Inside your returnButton.setOnAction method
        returnButton.setOnAction(event -> {
            // Close the current window and open the AdminGUI window
            primaryStage.close();

            // Open the AdminGUI window
            AdminGUI adminGUI = new AdminGUI();
            adminGUI.start(new Stage());
        });

        // Set the scene
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Product");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
