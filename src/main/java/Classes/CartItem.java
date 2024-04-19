package Classes;

public class CartItem {
    private ProductVariation productVariation;
    private int quantity;

    public CartItem(ProductVariation productVariation, int quantity) {
        this.productVariation = productVariation;
        this.quantity = quantity;
    }

    // Getters and setters
    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
