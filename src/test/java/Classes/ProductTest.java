package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        // Reset lastGeneratedId before each test
        Product.resetLastGeneratedId();
        // Initialize a new product instance before each test
        product = new Product("Product 2", "Description 2", "Category 2",
                "Color 2", 10, "Images/shoe3.png", 20.0);
    }

    @Test
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
    void testSettersAndGetters() {
        // Test setters and getters for all properties
        product.setProductID("3");
        assertEquals("3", product.getProductID());

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

        product.setImageUrl("Images/shoe1.jpg");
        assertEquals("Images/shoe1.jpg", product.getImageUrl());

        product.setPrice(25.0);
        assertEquals(25.0, product.getPrice());
    }

    @Test
    void testGenerateProductID() {
        // Generate three product IDs and verify they are unique and in the expected format
        String id1 = product.generateProductID();
        String id2 = product.generateProductID();
        String id3 = product.generateProductID();

        assertEquals("P0002", id1);
        assertEquals("P0003", id2);
        assertEquals("P0004", id3);
    }
}
