package Classes;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        // Initialize a new customer object before each test
        Shop shop = new Shop();
        List<CartItem> cart = new ArrayList<>();
List<Order> pastOrders = new ArrayList<>();
customer = new Customer(15, "kareem@gmial.com", "123", "kareem", "ahmed", "sdsds", cart, 0, pastOrders);
}


@Nested
@DisplayName("addToCart method")
class AddToCartTests {

    @ParameterizedTest
    @CsvSource({
            " 5, Product added to cart.",
            " 0,Error: Invalid quantity.",
            " 10 ,Product added to cart.",
            "3, Product added to cart.",
            "-1, Error: Invalid quantity.",
            "15 ,Error: Insufficient quantity."
    })
    void testAddToCart( int quantity, String expected) {
        Product product = new Product( "Product 50", "Description 1", "Category 1", "Color 1", 10, "Images/download.jpg", 10.0);

        Shop.products.add(product);
        

        String result = customer.addToCart(product, quantity);
        assertEquals(expected, result);
    }
}

@Nested
@DisplayName("removeFromCart method")
class RemoveFromCartTests {

    @RepeatedTest(5)
    void testRemoveFromCart() {
        // Prepare test data
        Product product = new Product( "Product 200", "Description 1", "Category 1", "Color 1", 10, "Images/download.jpg", 10.0);
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
}