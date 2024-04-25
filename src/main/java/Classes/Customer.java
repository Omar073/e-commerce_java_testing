package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
	private List<CartItem> cart;
	private int cartSubtotal;
	private List<Order> pastOrders;

	public Customer(int id, String email, String password, String firstName, String lastName, 
	String address, List<CartItem> cart, int cartSubtotal, List<Order> pastOrders) {
		super(id, email, password, firstName, lastName, address);
	    this.cart = cart != null ? cart : new ArrayList<>(); // Initialize the cart list
		this.cartSubtotal = cartSubtotal;
		this.pastOrders = pastOrders;
	}

	// setters and getters
	public List<CartItem> getCart() {
		return cart;
	}

	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

	public int getCartSubtotal() {
		return cartSubtotal;
	}

	public void setCartSubtotal(int cartSubtotal) {
		this.cartSubtotal = cartSubtotal;
	}

	public List<Order> getPastOrders() {
		return pastOrders;
	}

	public void setPastOrders(List<Order> pastOrders) {
		this.pastOrders = pastOrders;
	}
	public String addToCart(Product p, int quantity) {
		// Check if quantity is valid
		if (quantity <= 0) {
			return "Error: Invalid quantity.";
		}
	
		// Check if the product exists in the products list
		boolean productExists = false;
		for (Product product : Shop.products) {
			if (product.getProductID() == p.getProductID()) {
				productExists = true;
				break;
			}
		}
	
		if (!productExists) {
			return "Error: Product not found.";
		}
	
		// Check if the desired quantity is available
		if (p.getQuantity() < quantity) {
			return "Error: Insufficient quantity.";
		}
	
		if (cart == null) {
			cart = new ArrayList<>(); // Initialize the cart list if it's null
		}
	
		// Check if the product already exists in the cart
		for (CartItem item : cart) {
			if (item.getProduct().getProductID() == p.getProductID()) {
				item.setQuantity(item.getQuantity() + quantity);
				cartSubtotal += p.getPrice() * quantity;
	
				p.setQuantity(p.getQuantity() - quantity);
				return "Product added to cart.";
			}
		}
	
		cart.add(new CartItem(p, quantity));
		cartSubtotal += p.getPrice() * quantity;
	
		p.setQuantity(p.getQuantity() - quantity);
	
		return "Product added to cart.";
	}
	
	public void removeFromCart(CartItem p) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductID() == p.getProduct().getProductID()) {
				cart.remove(i);
				break;
			}
		}
		cartSubtotal -= p.getProduct().getPrice(); 
	}

	public void emptyCart() {
		cart.clear();
	}

	public boolean placeOrder(){
		String orderIdString = Integer.toString(this.getId()) + "_" + Integer.toString(pastOrders.size() + 1);
		
		try {
			// Attempt to create the order
			LocalDateTime orderTimestamp = LocalDateTime.now();
			Order order = new Order(orderIdString, orderTimestamp, this.getAddress());
	
			// Iterate through the products in the cart and create CartItem objects
			for (CartItem item : cart) { // Assuming each variation is ordered once
				order.addOrderItem(item); // Add the order item to the order
			}
	
			pastOrders.add(order); // Add the order to the list of past orders
			cart.clear(); // Clear the cart
	
			return true; // Return true indicating successful order creation
		} catch (NumberFormatException e) {
			// If there's a NumberFormatException, return false indicating failure
			System.out.println("Error: Unable to parse order ID. Invalid format.");
			return false;
		}
	}
	
	

	
}