package br.com.alura.ecommerce;

import java.math.BigDecimal;

public class Order {

	@SuppressWarnings("unused")
	private final String orderId;
	@SuppressWarnings("unused")
	private final BigDecimal amount;
	private final String email;

	public Order(String orderId, BigDecimal amount, String email) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.email = email;
	}
}
