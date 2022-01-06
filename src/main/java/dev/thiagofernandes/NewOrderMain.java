package dev.thiagofernandes;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.Uuid;

public class NewOrderMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try (var dipatcher = new KafkaDispatcher()) {

			for (var i = 0; i < 10; i++) {
				var key = Uuid.randomUuid().toString();

				var value = key + ",13123,333323";
				dipatcher.send("ECOMMERCE_NEW_ORDER", key, value);

				var email = "Thanks! We are processing your order!";
				dipatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
			}
		}
	}

}
