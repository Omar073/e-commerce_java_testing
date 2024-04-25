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
        Product product = new Product("2", "Product 2", "Description 2", "Category 2", "Color 2", 10, "https://via.placeholder.com/150", 20.0);
        int initialCartSize = customer.getCart().size();

        // Add a product to the cart
        customer.addToCart(product, 2);

        // Check if the product is added to the cart and cart subtotal is updated correctly
        assertEquals(initialCartSize + 1, customer.getCart().size());
        assertEquals(20, customer.getCartSubtotal());
    }

    @Test
    void testPlaceOrder() {
        Product product1 = new Product("3", "Product 3", "Description 3", "Category 3", "Color 2", 10, "https://via.placeholder.com/150", 20.0);
        Product product2 = new Product("4", "Product 4", "Description 4", "Category 4", "Color 2", 30, "https://via.placeholder.com/150", 20.0);

        // Add products to the cart
        customer.addToCart(product1, 2);
        customer.addToCart(product2, 1);

        int initialPastOrdersSize = pastOrders.size();

        // Place an order
        customer.placeOrder();

        // Check if the order is placed and the cart is cleared
        assertEquals(initialPastOrdersSize + 1, pastOrders.size());
        assertTrue(customer.getCart().isEmpty());

        // Check if the order item details are correct
        Order lastOrder = pastOrders.get(pastOrders.size() - 1);
        assertEquals(String.valueOf(customer.getId()) + "_" + (initialPastOrdersSize + 1), lastOrder.getOrderId());
        assertEquals(LocalDateTime.now().getDayOfYear(), lastOrder.getOrderTimestamp().getDayOfYear()); // Check day of year for simplicity
        assertEquals("123 Main St", lastOrder.getShippingAddress());
        assertEquals(2, lastOrder.getOrderItems().size()); // Check if both items are added to the order
    }
}
