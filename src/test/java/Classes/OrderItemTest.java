package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    private CartItem orderItem;

    @BeforeEach
    void setUp() {
        // Initialize a new order item instance before each test
        ProductVariation productVariation = new ProductVariation(1, 1, 10, 5, true, null);
        orderItem = new CartItem(productVariation, 2);
    }

    @Test
    void testGetProductVariation() {
        // Check if the correct product variation is returned
        assertEquals(1, orderItem.getProductVariation().getVariationId());
    }

    @Test
    void testGetQuantity() {
        // Check if the correct quantity is returned
        assertEquals(2, orderItem.getQuantity());
    }

    @Test
    void testSetProductVariation() {
        // Set a new product variation and check if it's set correctly
        ProductVariation newProductVariation = new ProductVariation(2, 1, 20, 3, true, null);
        orderItem.setProductVariation(newProductVariation);
        assertEquals(2, orderItem.getProductVariation().getVariationId());
    }

    @Test
    void testSetQuantity() {
        // Set a new quantity and check if it's set correctly
        orderItem.setQuantity(3);
        assertEquals(3, orderItem.getQuantity());
    }
}
