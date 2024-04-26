package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer extends Person {
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

	public boolean removeFromCart(CartItem itemToRemove) {
		Iterator<CartItem> iterator = cart.iterator();
		while (iterator.hasNext()) {
			CartItem item = iterator.next();
			if (item.getProduct().getProductID() == itemToRemove.getProduct().getProductID()) {
				// Increment product quantity by the quantity in the cart
				itemToRemove.getProduct().setQuantity(itemToRemove.getProduct().getQuantity() + item.getQuantity());
				// Update cart subtotal
				cartSubtotal -= item.getProduct().getPrice() * item.getQuantity();
				iterator.remove();
				return true; // Successfully removed the item
			}
		}
		return false; // Item not found in the cart
	}

	public String increaseQuantity(CartItem cartItem) {
		Product product = cartItem.getProduct();
		int currentQuantity = cartItem.getQuantity();
		int maxQuantity = product.getQuantity();

		if (maxQuantity >= 1) {
			cartItem.setQuantity(currentQuantity + 1);
			product.setQuantity(maxQuantity - 1);
			return "Quantity increased.";
		} else {

			return "Cannot increase quantity. Product stock limit reached.";
		}
	}

	public String decreaseQuantity(CartItem cartItem) {
		int currentQuantity = cartItem.getQuantity();

		if (currentQuantity > 1) {
			cartItem.setQuantity(currentQuantity - 1);
			return "Quantity decreased.";
		} else {
			return "Cannot decrease quantity further.";
		}
	}

	public void emptyCart() {
		for (CartItem item : cart) {
			Product product = item.getProduct();
			product.setQuantity(product.getQuantity() + item.getQuantity());
		}
		cart.clear();
		cartSubtotal = 0;
	}

	public boolean placeOrder() {
		String orderIdString = Integer.toString(this.getId()) + "_" + Integer.toString(pastOrders.size() + 1);

		try {
			// Attempt to create the order
			LocalDateTime orderTimestamp = LocalDateTime.now();
			Order order = new Order(orderIdString, orderTimestamp, this.getAddress());

			// Iterate through the products in the cart and create CartItem objects
			for (CartItem item : cart) { // Assuming each variation is ordered once
				order.addOrderItem(item); // Add the order item to the order
				System.out.println("Added item to order: " + item.getProduct().getProductName());
			}

			pastOrders.add(order); // Add the order to the list of past orders
			cart.clear();
			cartSubtotal = 0; // Clear the cart

			System.out.println("Order placed successfully.");

			return true; // Return true indicating successful order creation
		} catch (NumberFormatException e) {
			// If there's a NumberFormatException, return false indicating failure
			System.out.println("Error: Unable to parse order ID. Invalid format.");
			return false;
		}
	}

}