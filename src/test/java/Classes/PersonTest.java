package Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person(1, "test@email.com", "password", "John", "Doe", "123 Street");
    }

    @Test
    public void testId() {
        person.setId(2);
        assertEquals(2, person.getId());
    }

    @Test
    public void testEmail() {
        person.setEmail("new@email.com");
        assertEquals("new@email.com", person.getEmail());
    }

    @Test
    public void testPassword() {
        person.setPassword("newPassword");
        assertEquals("newPassword", person.getPassword());
    }

    @Test
    public void testFirstName() {
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    public void testLastName() {
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }

    @Test
    public void testAddress() {
        person.setAddress("456 Avenue");
        assertEquals("456 Avenue", person.getAddress());
    }
}