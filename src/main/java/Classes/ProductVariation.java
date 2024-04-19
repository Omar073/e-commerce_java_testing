package Classes;

import java.util.List;

public class ProductVariation {
    private int variationId;
    private int productId;
    private int price;
    private int quantity;
	private int rating;
    private boolean inStock;
    private List<ProductPropertyandValue> productPropertiesValues; // each property and it's value of a single variation

    public ProductVariation(int variationId, int productId, int price, int quantity, int rating, boolean inStock, List<ProductPropertyandValue> productPropertiesValues) {
        this.variationId = variationId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
        this.inStock = inStock;
        this.productPropertiesValues = productPropertiesValues;
    }

    //setters and getters
    public int getVariationId() {
        return variationId;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }
    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public List<ProductPropertyandValue> getProductPropertiesValues() {
        return productPropertiesValues;
    }


}