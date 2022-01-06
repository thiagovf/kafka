package dev.thiagofernandes;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.Uuid;

public class NewOrderMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try (var orderDipatcher = new KafkaDispatcher<Order>()) {
			try (var emailDipatcher = new KafkaDispatcher<String>()) {

				for (var i = 0; i < 10; i++) {
					var userId = Uuid.randomUuid().toString();
					var orderID = Uuid.randomUuid().toString();
					var amount = new BigDecimal(Math.random() * 5000 + 1);

					var order = new Order(userId, orderID, amount);

					orderDipatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

					var email = "Thanks! We are processing your order!";
					emailDipatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
				}
			}
		}
	}

}
