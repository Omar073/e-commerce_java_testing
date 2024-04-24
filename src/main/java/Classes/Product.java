package Classes;

import java.util.List;

public class Product {
    private String productID;
    private String productName;
    private String description;
    private String category;
	private List<ProductVariation> _variations; // Non-nullable
	private List<ProductProperty> _availableProperties; // What properties are offered
    private String imageUrl  ;
    private String price;

    public Product(String productID, String productName, String description, String category, List<ProductVariation> _variations, List<ProductProperty> _availableProperties,String imageUrl , String price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this._variations = _variations;
        this._availableProperties = _availableProperties;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    //setters and getters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
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

    public List<ProductVariation> get_variations() {
        return _variations;
    }

    public void set_variations(List<ProductVariation> _variations) {
        this._variations = _variations;
    }

    public List<ProductProperty> get_availableProperties() {
        return _availableProperties;
    }

    public void set_availableProperties(List<ProductProperty> _availableProperties) {
        this._availableProperties = _availableProperties;
    }

    public void addVariation(ProductVariation variation) {
        _variations.add(variation);
    }

    public void removeVariation(ProductVariation variation) {
        
        // search in list of variation for this varaitions id
        for (int i = 0; i < _variations.size(); i++) {
            if (_variations.get(i).getProductId() == variation.getProductId()) {
                _variations.remove(i);
                break;
            }
        }
    }

    public void addProperty(ProductProperty property) {
        _availableProperties.add(property);
    }

    public void removeProperty(ProductProperty property) {
        _availableProperties.remove(property);
    }

    public ProductVariation getVariation(int index) {
        return _variations.get(index);
    }

    public ProductProperty getProperty(int index) {
        return _availableProperties.get(index);
    }

}