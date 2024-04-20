package Classes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;
    private List<Order> pastOrders;

    @BeforeEach
    void setUp() {
        // Initialize a new customer instance before each test
        pastOrders = new ArrayList<>();
        customer = new Customer(1, "test@example.com", "password", "John", "Doe",
                                "123 Main St", new ArrayList<>(), 0, pastOrders);
    }

    @Test
    void testAddToCart() {
        ProductVariation productVariation = new ProductVariation(1, 1, 10, 5, true, null);
        int initialCartSize = customer.getCart().size();

        // Add a product to the cart
        customer.addToCart(productVariation, 2);

        // Check if the product is added to the cart and cart subtotal is updated correctly
        assertEquals(initialCartSize + 1, customer.getCart().size());
        assertEquals(20, customer.getCartSubtotal());
    }

    @Test
    void testPlaceOrder() {
        ProductVariation productVariation1 = new ProductVariation(1, 1, 10, 5, true, null);
        ProductVariation productVariation2 = new ProductVariation(2, 1, 20, 3, true, null);

        // Add products to the cart
        customer.addToCart(productVariation1, 2);
        customer.addToCart(productVariation2, 1);

        int initialPastOrdersSize = pastOrders.size();

        // Place an order
        customer.placeOrder();

        // Check if the order is placed and the cart is cleared
        assertEquals(initialPastOrdersSize + 1, pastOrders.size());
        assertTrue(customer.getCart().isEmpty());

        // Check if the order item details are correct
        Order lastOrder = pastOrders.get(pastOrders.size() - 1);
        assertEquals(String.valueOf(customer.getId()) + (initialPastOrdersSize + 1), lastOrder.getOrderId());
        assertEquals(LocalDateTime.now().getDayOfYear(), lastOrder.getOrderTimestamp().getDayOfYear()); // Check day of year for simplicity
        assertEquals("123 Main St", lastOrder.getShippingAddress());
        assertEquals(2, lastOrder.getOrderItems().size()); // Check if both items are added to the order
    }
}
