package dev.thiagofernandes;

import java.math.BigDecimal;

public class Order {

	private final String userId, orderId;
	private final BigDecimal amount;
	
	public Order(String userId, String orderId, BigDecimal amount) {
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}
}
