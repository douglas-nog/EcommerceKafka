package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {


	private final String userId, orderId;
	private final BigDecimal amount;
	private final String email;

	public Order(String userId, String orderId, BigDecimal amount, String email) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.amount = amount;
		this.email = email;
	}
    public String getEmail() {
		return email;
    }

	public String getUserId() {
		return userId;
	}
}
