package Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShopTest {
    private Shop shop;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }

    @Test
    public void testConstructor() {
        assertNotNull(shop);
        assertEquals(10, Shop.products.size());
        assertEquals(10, Shop.persons.size());
    }

    @Test
    public void testSetAndGetLoggedInPerson() {
        Person person = new Admin(1, "admin1@example.com", "admin123", "Admin", "Smith", "123 Admin St");
        Shop.setLoggedInPerson(person);
        assertEquals(person, Shop.getLoggedInPerson());
    }
}