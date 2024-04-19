package Classes;

import java.util.List;

public class Customer {
	private int id;
	private String email;
	private String password;
	private List<Product> cart;
	private int rating;

	public Customer(int id, String email, String password, List<Product> cart, int rating) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.cart = cart;
		this.rating = rating;
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

	public List<Product> getCart() {
		return cart;
	}

	public void setCart(List<Product> cart) {
		this.cart = cart;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


	
}