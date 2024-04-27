package Classes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

    private Order order;
    private List<CartItem> orderItems;

    @BeforeEach
    void setUp() {
        // Initialize a new order instance before each test
        orderItems = new ArrayList<>();
        order = new Order("1", LocalDateTime.now(), "123 Main St");
        order.setOrderItems(orderItems);
    }

    
    @Test
    void testSetAndGetOrderId() {
        // Set a new order ID and check if it's set correctly
        order.setOrderId("2");
        assertEquals("2", order.getOrderId());
    }

    @Test
    void testSetAndGetOrderTimestamp() {
        // Set a new order timestamp and check if it's set correctly
        LocalDateTime newTimestamp = LocalDateTime.of(2023, 4, 15, 12, 30);
        order.setOrderTimestamp(newTimestamp);
        assertEquals(newTimestamp, order.getOrderTimestamp());
    }

    @Test
    void testSetAndGetOrderItems() {
        // Set a new list of order items and check if it's set correctly
        List <CartItem> newOrderItems = new ArrayList<>();
        Product product1 = new Product("Product 1", "Description 1", "Category 1", "Color 1", 10, "Images/shoe1.jpeg", 20.0);
        Product product2 = new Product("Product 2", "Description 2", "Category 2", "Color 2", 10, "Images/shoe2.jpeg", 30.0);
        CartItem item1 = new CartItem(product1, 1);
        CartItem item2 = new CartItem(product2, 2);
        newOrderItems.add(item1);
        newOrderItems.add(item2);

        order.setOrderItems(newOrderItems);

        // Check if the list of order items is set correctly
        assertEquals(newOrderItems, order.getOrderItems());
    }

    @Test
    void testSetAndGetOrderSubtotal() {
        // Set a new order subtotal and check if it's set correctly
        order.setOrderSubtotal(150.0);
        assertEquals(150.0, order.getOrderSubtotal());
    }

    @Test
    void testSetAndGetShippingAddress() {
        // Set a new shipping address and check if it's set correctly
        order.setShippingAddress("456 Elm St");
        assertEquals("456 Elm St", order.getShippingAddress());
    }

    @Test
    void testRecalculateOrderSubtotal() {
        // Create two products and corresponding order items
        Product product1 = new Product("Product 1", "Description 1", "Category 1", "Color 1", 10, "Images/shoe1.jpeg", 20.0);
        Product product2 = new Product("Product 2", "Description 2", "Category 2", "Color 2", 10, "Images/shoe2.jpeg", 30.0);
        CartItem item1 = new CartItem(product1, 1);
        CartItem item2 = new CartItem(product2, 2);

        // Add the order items to the order
        orderItems.add(item1);
        orderItems.add(item2);

        // Calculate the expected order subtotal
        double expectedSubtotal = (product1.getPrice() * item1.getQuantity()) + (product2.getPrice() * item2.getQuantity());

        // Recalculate the order subtotal
        order.recalculateOrderSubtotal();

        // Check if the order subtotal is recalculated correctly
        assertEquals(expectedSubtotal, order.getOrderSubtotal());
    }

    @Test
    void testAddOrderItem() {
        Product product = new Product( "Product 2", "Description 2", "Category 2", 
        "Color 2", 10, "Images/shoe3.png", 20.0);
        CartItem orderItem = new CartItem(product, 2);

        // Add an order item to the order
        order.addOrderItem(orderItem);

        // Check if the order item is added and the order subtotal is updated correctly
        assertEquals(1, order.getOrderItems().size());
        assertEquals(40.0, order.getOrderSubtotal());
    }

    @Test
    void testRemoveOrderItem() {
        Product product = new Product( "Product 2", "Description 2", "Category 2", "Color 2", 10,
                "Images/shoe2.jpeg", 20.0);
        CartItem orderItem = new CartItem(product, 2);
        order.addOrderItem(orderItem);

        // Remove the order item from the order
        order.removeOrderItem(orderItem);

        // Check if the order item is removed and the order subtotal is updated
        // correctly
        assertTrue(order.getOrderItems().isEmpty());
        assertEquals(0.0, order.getOrderSubtotal());
    }
}
