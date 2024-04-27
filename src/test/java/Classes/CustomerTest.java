package Classes;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        // Initialize a new customer object before each test
        Shop shop = new Shop();
        List<CartItem> cart = new ArrayList<>();
        List<Order> pastOrders = new ArrayList<>();
        customer = new Customer(15, "kareem@gmial.com", "123", "kareem", "ahmed", "sdsds", cart, 0, pastOrders);
    }

    @Test
    @DisplayName("Test Getters and Setters")
    void testGettersAndSetters() {
        // Create a Customer object
        Customer customer = new Customer(1, "test@example.com", "password", "John", "Doe", "123 Main St",
                null, 0, null);

        // Test setters
        customer.setId(2);
        customer.setEmail("newemail@example.com");
        customer.setPassword("newpassword");
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setAddress("456 Elm St");

        // Test getters
        assertEquals(2, customer.getId());
        assertEquals("newemail@example.com", customer.getEmail());
        assertEquals("newpassword", customer.getPassword());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("456 Elm St", customer.getAddress());
    }

    


    @Nested
    @DisplayName("removeFromCart method")
    class RemoveFromCartTests {

        @RepeatedTest(5)
        void testRemoveFromCart() {
            // Prepare test data
            Product product = new Product("Product 200", "Description 1", "Category 1", "Color 1", 10,
                    "Images/download.jpg", 10.0);
            Shop.products.add(product);
            customer.addToCart(product, 3);
            List<CartItem> cartItems = customer.getCart();

            assertEquals(7, product.getQuantity());

            CartItem item = null;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().equals(product)) {
                    item = cartItem;
                    break;
                }
            }

            if (item == null) {
                throw new AssertionError("Item not found in cart.");
            }

            boolean result = customer.removeFromCart(item);

            assertEquals(true, result);
            assertEquals(10, product.getQuantity());
        }
    }

    @Nested
    @DisplayName("increaseQuantity method")
    class IncreaseQuantityTests {

        @Test
        void testIncreaseQuantity_Valid() {
            // Prepare test data
            Product product = new Product("Product 1", "Description", "Category", "Color", 10, "image.jpg", 20.0);
            CartItem cartItem = new CartItem(product, 5);

            // Call the method
            String result = customer.increaseQuantity(cartItem);

            // Check the result
            assertEquals("Quantity increased.", result);
            assertEquals(6, cartItem.getQuantity());
            assertEquals(9, product.getQuantity()); // Max quantity decreased by 1
        }

        @Test
        void testIncreaseQuantity_Invalid() {
            // Prepare test data
            Product product = new Product("Product 1", "Description", "Category", "Color", 0, "image.jpg", 20.0);
            CartItem cartItem = new CartItem(product, 5);

            // Call the method
            String result = customer.increaseQuantity(cartItem);

            // Check the result
            assertEquals("Cannot increase quantity. Product stock limit reached.", result);
            assertEquals(5, cartItem.getQuantity());
            assertEquals(0, product.getQuantity()); // Max quantity remains 0
        }
    }

    @Nested
    @DisplayName("decreaseQuantity method")
    class DecreaseQuantityTests {

        @Test
        void testDecreaseQuantity_Valid() {
            // Prepare test data
            Product product = new Product("Product 1", "Description", "Category", "Color", 10, "image.jpg", 20.0);
            CartItem cartItem = new CartItem(product, 5);

            // Call the method
            String result = customer.decreaseQuantity(cartItem);

            // Check the result
            assertEquals("Quantity decreased.", result);
            assertEquals(4, cartItem.getQuantity());
        }

        @Test
        void testDecreaseQuantity_MinimumReached() {
            // Prepare test data
            Product product = new Product("Product 1", "Description", "Category", "Color", 10, "image.jpg", 20.0);
            CartItem cartItem = new CartItem(product, 1);

            // Call the method
            String result = customer.decreaseQuantity(cartItem);

            // Check the result
            assertEquals("Cannot decrease quantity further.", result);
            assertEquals(1, cartItem.getQuantity());
        }
    }

    @Nested
    @DisplayName("emptyCart method")
    class EmptyCartTests {

        @Test
        void testEmptyCart() {
            // Prepare test data
            Product product1 = new Product("Product 1", "Description", "Category", "Color", 10, "image1.jpg", 20.0);
            Product product2 = new Product("Product 2", "Description", "Category", "Color", 5, "image2.jpg", 30.0);
            CartItem cartItem1 = new CartItem(product1, 3);
            CartItem cartItem2 = new CartItem(product2, 2);
            customer.getCart().add(cartItem1);
            customer.getCart().add(cartItem2);

            // Call the method
            customer.emptyCart();

            // Check the result
            assertTrue(customer.getCart().isEmpty());
            assertEquals(13, product1.getQuantity()); // Quantity of product1 restored
            assertEquals(7, product2.getQuantity()); // Quantity of product2 restored
            assertEquals(0, customer.getCartSubtotal()); // Cart subtotal reset to 0
        }
    }

    @Nested
    @DisplayName("placeOrder method")
    class PlaceOrderTests {


        @Test
        void testPlaceOrder_Successful() {
            // Prepare test data
            Product product1 = new Product("Product 1", "Description 1",
                    "Category 1", "Color 1", 10, "image1.jpg", 20.0);
            Product product2 = new Product("Product 2", "Description 2",
                    "Category 2", "Color 2", 5, "image2.jpg", 30.0);

            Shop.products.add(product1);
            Shop.products.add(product2);

            assertEquals("Product added to cart.", customer.addToCart(product1, 3));
            assertEquals("Product added to cart.", customer.addToCart(product2, 2));

            // Call the method
            boolean result = customer.placeOrder();
            // System.out.println(pastOrders.size());
            // assertEquals(1, customer.getPastOrders().size());

            // Check the result
            assertTrue(result); // Order creation should be successful
            assertTrue(customer.getCart().isEmpty()); // Cart should be empty after placing the order
            assertEquals(0, customer.getCartSubtotal()); // Cart subtotal should be reset to 0
            assertEquals(1, customer.getPastOrders().size()); // One order should be added to pastOrders list
            Order order = customer.getPastOrders().get(0);
            assertEquals("15_1", order.getOrderId()); // Check if the order ID is correctly generated
            assertEquals(customer.getAddress(), order.getShippingAddress()); // Check if the shipping address is correct
            assertEquals(2, order.getOrderItems().size()); // Check if all cart items are added to the order
            assertEquals(7, product1.getQuantity()); // Check if product quantities are updated correctly
            assertEquals(3, product2.getQuantity());
        }
    }


    @Nested 
    @DisplayName("Test Getters and Setters")

    class GettersAndSettersTests {

        

    @ParameterizedTest
    @CsvSource({
        "5, 'Product added to cart.'",
        "0, 'Error: Invalid quantity.'",
        "-1, 'Error: Invalid quantity.'",
        "11, 'Error: Insufficient quantity.'"
    })
    
    void testAddToCart(int quantity, String expected) {
        Product product = new Product("Product 50", "Description 1", "Category 1", "Color 1", 10,
                "Images/download.jpg", 10.0);
        Shop.products.add(product);
    
        String result = customer.addToCart(product, quantity);
    
        assertEquals(expected, result);
    }
}
}


