package Classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        // Initialize a new product instance before each test
        List<ProductVariation> variations = new ArrayList<>();
        List<ProductProperty> properties = new ArrayList<>();
        product = new Product("123", "Test Product", "Description", "Category", variations, properties , "imageUrl" , 20);
    }

    @Test
    void testAddVariation() {
        ProductVariation variation = new ProductVariation(1, 123, 10, 5, true, null);

        // Add a variation to the product
        product.addVariation(variation);

        // Check if the variation is added
        assertEquals(1, product.get_variations().size());
    }

    @Test
    void testRemoveVariation() {
        ProductVariation variation = new ProductVariation(1, 123, 10, 5, true, null);
        product.addVariation(variation);

        // Remove the variation from the product
        product.removeVariation(variation);

        // Check if the variation is removed
        assertEquals(0, product.get_variations().size());
    }

    @Test
    void testAddProperty() {
        ProductProperty property = new ProductProperty("Color");

        // Add a property to the product
        product.addProperty(property);

        // Check if the property is added
        assertEquals(1, product.get_availableProperties().size());
    }

    @Test
    void testRemoveProperty() {
        ProductProperty property = new ProductProperty("Color");
        product.addProperty(property);

        // Remove the property from the product
        product.removeProperty(property);

        // Check if the property is removed
        assertEquals(0, product.get_availableProperties().size());
    }

    // Test getters and setters for other attributes

}
