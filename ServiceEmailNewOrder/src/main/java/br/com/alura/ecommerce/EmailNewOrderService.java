package br.com.alura.ecommerce;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.com.alura.ecommerce.consumer.KafkaService;
import br.com.alura.ecommerce.dispatcher.KafkaDispatcher;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailNewOrderService {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		var emailService = new EmailNewOrderService();
		try (var service = new KafkaService<>(EmailNewOrderService.class.getSimpleName(),
				"ECOMMERCE_NEW_ORDER",
				emailService::parse,
				Map.of())) {
			service.run();
		}
	}

	private final KafkaDispatcher<String> emailDispatcher = new KafkaDispatcher<>();

	private void parse(ConsumerRecord<String, Message<Order>> record) throws ExecutionException, InterruptedException {
		System.out.println("------------------------------------------");
		System.out.println("Processing new order, preparing email");
		var message = record.value();
		System.out.println(message);

		var emailCode = "Thak you for your order! We are processing your order!";
		var order = message.getPayload();
		var id = message.getId().continueWith(EmailNewOrderService.class.getSimpleName());

		emailDispatcher.send("ECOMMERCE_SEND_EMAIL", order.getEmail(),
				id, emailCode);
		}
	}


