package Classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        // Initialize a new order item instance before each test
        Product product = new Product( "Product 2", "Description 2", "Category 2", "Color 2", 10,
                "Images/shoe1.jpeg", 20.0);
        cartItem = new CartItem(product, 2);
    }

    @Test
    void testGetProduct() {
        // Check if the correct product is returned
        assertEquals("Product 2", cartItem.getProduct().getProductName());
        assertEquals("Description 2", cartItem.getProduct().getDescription());
        assertEquals("Category 2", cartItem.getProduct().getCategory());
        assertEquals("Color 2", cartItem.getProduct().getColor());
        assertEquals(10, cartItem.getProduct().getQuantity());
        assertEquals("Images/shoe1.jpeg", cartItem.getProduct().getImageUrl());
        assertEquals(20.0, cartItem.getProduct().getPrice());
    }

    @Test
    void testSetProduct() {
        // Create a new product and set it
        Product newProduct = new Product( "New Product", "New Description", 
        "New Category", "New Color", 5, "Images/hoodie1.jpeg", 30.0);
        cartItem.setProduct(newProduct);

        // Check if the product is set correctly
        assertEquals("New Product", cartItem.getProduct().getProductName());
        assertEquals("New Description", cartItem.getProduct().getDescription());
        assertEquals("New Category", cartItem.getProduct().getCategory());
        assertEquals("New Color", cartItem.getProduct().getColor());
        assertEquals(5, cartItem.getProduct().getQuantity());
        assertEquals("Images/hoodie1.jpeg", cartItem.getProduct().getImageUrl());
        assertEquals(30.0, cartItem.getProduct().getPrice());
    }

    @Test
    void testGetQuantity() {
        // Check if the correct quantity is returned
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    void testSetQuantity() {
        // Set a new quantity and check if it's set correctly
        cartItem.setQuantity(3);
        assertEquals(3, cartItem.getQuantity());
    }
}
