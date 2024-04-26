package Classes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        // Initialize a new product instance before each test
        product = new Product("Product 2", "Description 2", "Category 2", "Color 2", 10, "Images/shoe3.png", 20.0);
    }

    @Test
    @DisplayName("Constructor initializes properties correctly")
    void testConstructor() {
        // Verify that the constructor sets the properties correctly
        assertEquals("P0001", product.getProductID());
        assertEquals("Product 2", product.getProductName());
        assertEquals("Description 2", product.getDescription());
        assertEquals("Category 2", product.getCategory());
        assertEquals("Color 2", product.getColor());
        assertEquals(10, product.getQuantity());
        assertEquals("Images/shoe3.png", product.getImageUrl());
        assertEquals(20.0, product.getPrice());
    }

    @Test
    @DisplayName("Test setters and getters for all properties")
    void testSettersAndGetters() {
        // Test setters and getters for all properties
        product.setProductID("P0002");
        assertEquals("P0002", product.getProductID());

        product.setProductName("New Product Name");
        assertEquals("New Product Name", product.getProductName());

        product.setDescription("New Description");
        assertEquals("New Description", product.getDescription());

        product.setCategory("New Category");
        assertEquals("New Category", product.getCategory());

        product.setColor("New Color");
        assertEquals("New Color", product.getColor());

        product.setQuantity(15);
        assertEquals(15, product.getQuantity());

        product.setImageUrl("https://new-image-url.com");
        assertEquals("https://new-image-url.com", product.getImageUrl());

        product.setPrice(25.0);
        assertEquals(25.0, product.getPrice());
    }

    @Test
    @DisplayName("Test product ID generation")
    void testProductIDGeneration() {
        // Ensure that product IDs are generated correctly and incremented
        Product product1 = new Product("Product 3", "Description 3", "Category 3", "Color 3", 10, "Images/shoe4.png", 30.0);
        assertEquals("P0002", product1.getProductID());

        Product product2 = new Product("Product 4", "Description 4", "Category 4", "Color 4", 10, "Images/shoe5.png", 40.0);
        assertEquals("P0003", product2.getProductID());
    }
}
