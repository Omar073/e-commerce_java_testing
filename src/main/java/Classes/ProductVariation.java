package Classes;

import java.util.List;

public class ProductVariation {
    private int variationId;
    private int productId;
    private int price;
    private int quantity;
    private boolean inStock;
    private List<ProductPropertyandValue> productPropertiesValues; // each property and it's value of a single variation

    public ProductVariation(int variationId, int productId, int price, int quantity, boolean inStock, List<ProductPropertyandValue> productPropertiesValues) {
        this.variationId = variationId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
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