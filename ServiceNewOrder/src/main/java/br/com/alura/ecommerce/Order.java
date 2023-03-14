package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {

	@SuppressWarnings("unused")
	private final String userId, orderId;
	@SuppressWarnings("unused")
	private final BigDecimal amount;
	private final String email;

	public Order(String userId, String orderId, BigDecimal amount, String email) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.amount = amount;
		this.email = email;
	}
}
