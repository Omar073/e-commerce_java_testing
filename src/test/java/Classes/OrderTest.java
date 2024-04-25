package Classes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

    private Order order;
    private List<CartItem> orderItems;

    @BeforeEach
    void setUp() {
        // Initialize a new order instance before each test
        orderItems = new ArrayList<>();
        order = new Order(1, LocalDateTime.now(), "123 Main St");
        order.setOrderItems(orderItems);
    }

    @Test
    void testAddOrderItem() {
        Product product = new Product("2", "Product 2", "Description 2", "Category 2", "Color 2", 10, "https://via.placeholder.com/150", 20.0);
        CartItem orderItem = new CartItem(product, 2);

        // Add an order item to the order
        order.addOrderItem(orderItem);

        // Check if the order item is added and the order subtotal is updated correctly
        assertEquals(1, order.getOrderItems().size());
        assertEquals(20.0, order.getOrderSubtotal());
    }

    @Test
    void testRemoveOrderItem() {
        Product product = new Product("2", "Product 2", "Description 2", "Category 2", "Color 2", 10, "https://via.placeholder.com/150", 20.0);
        CartItem orderItem = new CartItem(product, 2);
        order.addOrderItem(orderItem);

        // Remove the order item from the order
        order.removeOrderItem(orderItem);

        // Check if the order item is removed and the order subtotal is updated correctly
        assertTrue(order.getOrderItems().isEmpty());
        assertEquals(0.0, order.getOrderSubtotal());
    }
}
