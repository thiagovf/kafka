package dev.thiagofernandes;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class EmailService {
	public static void main(String[] args) {
		var consumer = new KafkaConsumer<>(properties());
		consumer.subscribe(Collections.singletonList("ECOMMERCE_SEND_EMAIL"));
		while (true) {
			var records = consumer.poll(Duration.ofMillis(100));
			if (!records.isEmpty()) {
				System.out.println("Encontrei " + records.count() + " registros.");
				for (var record : records) {
					System.out.println("---------------------");
					System.out.println("Sending email");
					System.out.println(record.key());
					System.out.println(record.value());
					System.out.println(record.partition());
					System.out.println(record.offset());
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// ignoring
						e.printStackTrace();
					}
					System.out.println("Email sent.");
				}
			}
		}
	}

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getSimpleName());

		return properties;
	}
}
