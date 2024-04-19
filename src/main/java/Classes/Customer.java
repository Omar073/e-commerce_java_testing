package Classes;

import java.time.LocalDateTime;
import java.util.List;

public class Customer {
	private int id;
	private String email;
	private String password;
	private String address;
	private List<ProductVariation> cart;
	private int cartSubtotal;
	private List<Order> pastOrders;

	public Customer(int id, String email, String password, String address, 
	List<ProductVariation> cart, int cartSubtotal, List<Order> pastOrders) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.address = address;
		this.cart = cart;
		this.cartSubtotal = cartSubtotal;
		this.pastOrders = pastOrders;
	}

	// setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ProductVariation> getCart() {
		return cart;
	}

	public void setCart(List<ProductVariation> cart) {
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
	
	public void addToCart(ProductVariation p, int quantity) {
		cart.add(p);
		cartSubtotal += p.getPrice() * quantity;
	}
	
	public void removeFromCart(OrderItem p) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getVariationId() == p.getProductVariation().getVariationId()) {
				cart.remove(i);
				break;
			}
		}
	}

	public void emptyCart() {
		cart.clear();
	}

	public void placeOrder(){
		// set the new order id by concatenating the customer id and the number of orders + 1
		int orderId = Integer.parseInt(Integer.toString(id) + Integer.toString(pastOrders.size() + 1));
		LocalDateTime orderTimestamp = LocalDateTime.now();
		Order order = new Order(orderId, orderTimestamp, address);
	
		// Iterate through the products in the cart and create OrderItem objects
		for (ProductVariation variation : cart) {
			OrderItem orderItem = new OrderItem(variation, 1); // Assuming each variation is ordered once
			order.addOrderItem(orderItem); // Add the order item to the order
		}
	
		pastOrders.add(order); // Add the order to the list of past orders
		cart.clear(); // Clear the cart
	}
	

	
}