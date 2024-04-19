package Classes;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDateTime orderTimestamp;
    private List<CartItem> orderItems;
    private double orderSubtotal;
    private String shippingAddress;

    public Order(int orderId, LocalDateTime orderTimestamp, String shippingAddress) {
        this.orderId = orderId;
        this.orderTimestamp = orderTimestamp;
        this.orderSubtotal = 0.0;
        this.shippingAddress = shippingAddress;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    // Method to recalculate the order subtotal based on order items
    private void recalculateOrderSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : orderItems) {
            subtotal += item.getProductVariation().getPrice() * item.getQuantity();
        }
        this.orderSubtotal = subtotal;
    }
}

