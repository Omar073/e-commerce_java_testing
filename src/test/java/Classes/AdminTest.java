package Classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {

    @Test
    public void testAdmin() {
        Admin admin = new Admin(1, "test@email.com", "password", "First", "Last", "Address");

        assertEquals(1, admin.getId());
        assertEquals("test@email.com", admin.getEmail());
        assertEquals("password", admin.getPassword());
        assertEquals("First", admin.getFirstName());
        assertEquals("Last", admin.getLastName());
        assertEquals("Address", admin.getAddress());
    }
}