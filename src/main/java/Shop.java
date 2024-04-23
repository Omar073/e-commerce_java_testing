import java.util.ArrayList;
import java.util.Arrays;

import Classes.Admin;
import Classes.Customer;
import Classes.Person;
import Classes.Product;
import Classes.ProductVariation;
import Classes.ProductProperty;
import Classes.ProductPropertyandValue;

public class Shop {

    // array list of persons
    static ArrayList<Person> persons;

    // array list of books
    static ArrayList<Product> products;

    public Shop() {
        persons = new ArrayList<>();
        products = new ArrayList<>();

        // Adding Admins
        persons.add(new Admin(1, "admin1@example.com", "admin123", "Admin", "Smith", "123 Admin St"));
        persons.add(new Admin(2, "admin2@example.com", "admin456", "Admin", "Johnson", "456 Admin St"));
        persons.add(new Admin(3, "admin3@example.com", "admin789", "Admin", "Taylor", "789 Admin St"));
        persons.add(new Admin(4, "admin4@example.com", "admin101112", "Admin", "Brown", "101112 Admin St"));

        // Adding Customers
        persons.add(new Customer(1, "customer1@example.com", "customer123", "John", "Doe", "123 Main St",
                new ArrayList<>(), 0, new ArrayList<>()));
        persons.add(new Customer(2, "customer2@example.com", "customer456", "Alice", "Smith", "456 Elm St",
                new ArrayList<>(), 0, new ArrayList<>()));
        persons.add(new Customer(3, "customer3@example.com", "customer789", "Bob", "Johnson", "789 Oak St",
                new ArrayList<>(), 0, new ArrayList<>()));
        persons.add(new Customer(4, "customer4@example.com", "customer101", "Emma", "Wilson", "101 Main St",
                new ArrayList<>(), 0, new ArrayList<>()));
        persons.add(new Customer(5, "customer5@example.com", "customer112", "David", "Jones", "112 Elm St",
                new ArrayList<>(), 0, new ArrayList<>()));
        persons.add(new Customer(6, "customer6@example.com", "customer1314", "Olivia", "Martinez", "1314 Oak St",
                new ArrayList<>(), 0, new ArrayList<>()));

        // Adding Products
        products.add(new Product("Bag1", "Cool Bag", "Very cool bag", "Wearables",
                new ArrayList<>( // adding variations
                        Arrays.asList(
                                new ProductVariation(1, 1, 10, 5, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Black"),
                                                        new ProductPropertyandValue("Size", "Medium")))),
                                new ProductVariation(2, 1, 20, 3, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Blue"),
                                                        new ProductPropertyandValue("Size", "Large")))))),
                new ArrayList<>(Arrays.asList(new ProductProperty("Color"), new ProductProperty("Size")))));

        products.add(new Product("Shirt1", "Classic Shirt", "Classic shirt for men", "Apparel",
                new ArrayList<>( // adding variations
                        Arrays.asList(
                                new ProductVariation(3, 2, 30, 10, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "White"),
                                                        new ProductPropertyandValue("Size", "Medium")))),
                                new ProductVariation(4, 2, 40, 5, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Blue"),
                                                        new ProductPropertyandValue("Size", "Large")))))),
                new ArrayList<>(Arrays.asList(new ProductProperty("Color"), new ProductProperty("Size")))));

        products.add(new Product("Watch1", "Luxury Watch", "Luxury watch for men", "Watches",
                new ArrayList<>( // adding variations
                        Arrays.asList(
                                new ProductVariation(5, 3, 200, 3, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Gold"),
                                                        new ProductPropertyandValue("Size", "Large")))),
                                new ProductVariation(6, 3, 250, 2, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Silver"),
                                                        new ProductPropertyandValue("Size", "Medium")))))),
                new ArrayList<>(Arrays.asList(new ProductProperty("Color"), new ProductProperty("Size")))));

        // Adding Products
        products.add(new Product("Shoes1", "Running Shoes", "Comfortable running shoes", "Footwear",
                new ArrayList<>( // adding variations
                        Arrays.asList(
                                new ProductVariation(7, 4, 80, 8, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Black"),
                                                        new ProductPropertyandValue("Size", "US 9")))),
                                new ProductVariation(8, 4, 90, 5, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Gray"),
                                                        new ProductPropertyandValue("Size", "US 10")))))),
                new ArrayList<>(Arrays.asList(new ProductProperty("Color"), new ProductProperty("Size")))));

        products.add(new Product("Laptop1", "Gaming Laptop", "High-performance gaming laptop", "Electronics",
                new ArrayList<>( // adding variations
                        Arrays.asList(
                                new ProductVariation(9, 5, 1500, 3, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Black"),
                                                        new ProductPropertyandValue("Storage", "512GB SSD")))),
                                new ProductVariation(10, 5, 1800, 2, true,
                                        new ArrayList<>( // adding properties and values to each variation
                                                Arrays.asList(new ProductPropertyandValue("Color", "Silver"),
                                                        new ProductPropertyandValue("Storage", "1TB HDD")))))),
                new ArrayList<>(Arrays.asList(new ProductProperty("Color"), new ProductProperty("Storage")))));

        products.add(
                new Product("Headphones1", "Wireless Headphones", "Noise-canceling wireless headphones", "Electronics",
                        new ArrayList<>( // adding variations
                                Arrays.asList(
                                        new ProductVariation(11, 6, 100, 10, true,
                                                new ArrayList<>( // adding properties and values to each variation
                                                        Arrays.asList(new ProductPropertyandValue("Color", "Black"),
                                                                new ProductPropertyandValue("Connectivity",
                                                                        "Bluetooth")))),
                                        new ProductVariation(12, 6, 120, 7, true,
                                                new ArrayList<>( // adding properties and values to each variation
                                                        Arrays.asList(new ProductPropertyandValue("Color", "White"),
                                                                new ProductPropertyandValue("Connectivity",
                                                                        "Bluetooth")))))),
                        new ArrayList<>(
                                Arrays.asList(new ProductProperty("Color"), new ProductProperty("Connectivity")))));

    }
}