package Classes;

public class Product {
    private static int lastGeneratedId = 0;

    private String productID;
    private String productName;
    private String description;
    private String category;
    private String color;
    private int quantity;
    private String imageUrl;
    private double price;

    public Product(String productName, String description, String category,
            String color, int quantity, String imageUrl, double price) {
        this.productID = generateProductID();
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.color = color;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // setters and getters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    String generateProductID() {
        lastGeneratedId++; // Increment the last generated ID
        return "P" + String.format("%04d", lastGeneratedId); // Format the ID with leading zeros
    }

    // Reset lastGeneratedId before each test
    public static void resetLastGeneratedId() {
        lastGeneratedId = 0;
    }
}