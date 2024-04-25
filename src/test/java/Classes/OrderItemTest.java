package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        // Initialize a new order item instance before each test
        Product product = new Product( "Product 2", "Description 2", "Category 2", "Color 2", 10,
                "Images/shoe1.jpeg", 20.0);
        cartItem = new CartItem(product, 2);
    }

    @Test
    void testGetProductVariation() {
        // Check if the correct product variation is returned
        assertEquals(1, cartItem.getProduct().getProductID());
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
