package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private LocalDateTime orderTimestamp;
    private List<CartItem> orderItems;
    private double orderSubtotal;
    private String shippingAddress;

    public Order(String orderId, LocalDateTime orderTimestamp, String shippingAddress) {
        this.orderId = orderId;
        this.orderTimestamp = orderTimestamp;
        this.orderSubtotal = 0.0;
        this.shippingAddress = shippingAddress;
        this.orderItems = new ArrayList<>(); // Initialize the orderItems list
    }

    // Getters and setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setOrderTimestamp(LocalDateTime orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public List<CartItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(double orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // Method to add an order item to the order
    public void addOrderItem(CartItem p) {
        // CartItem orderItem = new CartItem(p, quantity);
        orderItems.add(p);
        recalculateOrderSubtotal();
    }

    // Method to remove an order item from the order
    public void removeOrderItem(CartItem orderItem) {
        orderItems.remove(orderItem);
        recalculateOrderSubtotal();
    }

    void recalculateOrderSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : orderItems) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        this.orderSubtotal = subtotal;
    }
}

