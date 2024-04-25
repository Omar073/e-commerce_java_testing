package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    private CartItem orderItem;

    @BeforeEach
    void setUp() {
        // Initialize a new order item instance before each test
        Product product = new Product("2", "Product 2", "Description 2", "Category 2", "Color 2", 10, "https://via.placeholder.com/150", 20.0);
        orderItem = new CartItem(product, 2);
    }

    @Test
    void testGetProductVariation() {
        // Check if the correct product variation is returned
        assertEquals(1, orderItem.getProduct().getProductID());
    }

    @Test
    void testGetQuantity() {
        // Check if the correct quantity is returned
        assertEquals(2, orderItem.getQuantity());
    }

    @Test
    void testSetQuantity() {
        // Set a new quantity and check if it's set correctly
        orderItem.setQuantity(3);
        assertEquals(3, orderItem.getQuantity());
    }
}
