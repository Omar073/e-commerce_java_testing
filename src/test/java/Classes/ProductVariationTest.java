// package Classes;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// class ProductVariationTest {

//     private ProductVariation productVariation;

//     @BeforeEach
//     void setUp() {
//         // Initialize a new ProductVariation instance before each test
//         List<ProductPropertyandValue> propertiesValues = new ArrayList<>();
//         productVariation = new ProductVariation(1, 123, 10, 5, true, propertiesValues);
//     }

//     @Test
//     void testSetQuantity() {
//         // Set the quantity of the product variation
//         productVariation.setQuantity(8);

//         // Check if the quantity is set correctly
//         assertEquals(8, productVariation.getQuantity());
//     }

//     @Test
//     void testSetInStock() {
//         // Set the inStock status of the product variation
//         productVariation.setInStock(false);

//         // Check if the inStock status is set correctly
//         assertFalse(productVariation.isInStock());
//     }

//     @Test
//     void testAddProductPropertyandValue() {
//         ProductPropertyandValue propertyValue = new ProductPropertyandValue("Color", "Red");

//         // Add a product property and value to the product variation
//         productVariation.getProductPropertiesValues().add(propertyValue);

//         // Check if the property and value are added
//         assertEquals(1, productVariation.getProductPropertiesValues().size());
//     }

//     @Test
//     void testRemoveProductPropertyandValue() {
//         ProductPropertyandValue propertyValue = new ProductPropertyandValue("Color", "Red");
//         productVariation.getProductPropertiesValues().add(propertyValue);

//         // Remove the property and value from the product variation
//         productVariation.getProductPropertiesValues().remove(propertyValue);

//         // Check if the property and value are removed
//         assertEquals(0, productVariation.getProductPropertiesValues().size());
//     }

//     // Test getters and setters for other attributes

//     // Add more test cases for other functionalities as needed
// }
