package Classes;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {

        // array list of persons
        public static ArrayList<Person> persons;

        // array list of books
        public static ArrayList<Product> products;

        // the current logged in person
        public static Person currentLoggedInPerson;



        public Shop() {
                persons = new ArrayList<>();
                products = new ArrayList<>();

                // Adding Admins
                persons.add(new Admin(1, "admin1@example.com", "admin123", "Admin", "Smith", "123 Admin St"));
                persons.add(new Admin(2, "admin2@example.com", "2", "Admin", "Johnson", "456 Admin St"));
                persons.add(new Admin(3, "admin3@example.com", "admin789", "Admin", "Taylor", "789 Admin St"));
                persons.add(new Admin(4, "admin4@example.com", "admin101112", "Admin", "Brown", "101112 Admin St"));

                // Adding Customers

                persons.add(new Customer(5, "customer1@example.com", "customer123", "John", "Doe", "123 Main St",
                                new ArrayList<>(), 0, new ArrayList<>()));
                persons.add(new Customer(6, "customer2@example.com", "66", "Alice", "Smith", "456 Elm St",
                                new ArrayList<>(), 0, new ArrayList<>()));
                persons.add(new Customer(7, "customer3@example.com", "customer789", "Bob", "Johnson", "789 Oak St",
                                new ArrayList<>(), 0, new ArrayList<>()));
                persons.add(new Customer(8, "customer4@example.com", "customer101", "Emma", "Wilson", "101 Main St",
                                new ArrayList<>(), 0, new ArrayList<>()));
                persons.add(new Customer(9, "customer5@example.com", "customer112", "David", "Jones", "112 Elm St",
                                new ArrayList<>(), 0, new ArrayList<>()));
                persons.add(new Customer(10, "customer6@example.com", "customer1314", "Olivia", "Martinez",
                                "1314 Oak St",
                                new ArrayList<>(), 0, new ArrayList<>()));

                // Adding Products
                products.add(new Product("1", "Product 1", "Description 1", "Category 1", "Color 1", 10, "Images/download.jpg", 10.0));
                products.add(new Product("2", "Product 2", "Description 2", "Category 2", "Color 2", 10, "Images/hoodie1.jpeg", 20.0));
                products.add(new Product("3", "Product 3", "Description 3", "Category 3", "Color 3", 10, "Images/hoodie2.jpeg", 30.0));
                products.add(new Product("4", "Product 4", "Description 4", "Category 4", "Color 4", 10, "Images/shoe1.jpeg", 40.0));
                products.add(new Product("5", "Product 5", "Description 5", "Category 5", "Color 5", 10, "Images/shoe2.jpeg", 50.0));
                products.add(new Product("6", "Product 6", "Description 6", "Category 6", "Color 6", 10, "Images/shoe1.jpg", 60.0));
                products.add(new Product("7", "Product 7", "Description 7", "Category 7", "Color 7", 10, "Images/shoe3.jpeg", 70.0));
                products.add(new Product("8", "Product 8", "Description 8", "Category 8", "Color 8", 10, "Images/shoe3.png", 80.0));
                products.add(new Product("9", "Product 9", "Description 9", "Category 9", "Color 9", 10, "Images/thumbsup.jpg", 90.0));
                products.add(new Product("10", "Product 10", "Description 10", "Category 10", "Color 10", 10, "Images/download.jpg", 100.0));

        }

        public static Person getLoggedInPerson() {
                return currentLoggedInPerson;
        }

        public static void setLoggedInPerson(Person person) {
                currentLoggedInPerson = person;
        }
}