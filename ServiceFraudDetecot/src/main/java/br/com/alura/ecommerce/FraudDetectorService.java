package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {

	public static void main(String[] args) {
		var fraudService = new FraudDetectorService();
		try (var service = new KafkaService<>(FraudDetectorService.class.getSimpleName(), 
				"ECOMMERCE_NEW_ORDER",
				fraudService::parse,
				Order.class,
				Map.of())) {
			service.run();
		}
	}

	private final KafkaDispatcher<Order> orderDispacher = new KafkaDispatcher<>();

	private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
		System.out.println("------------------------------------------");
		System.out.println("Processing new order, checking for fraud");
		System.out.println(record.key());
		System.out.println(record.value());
		System.out.println(record.partition());
		System.out.println(record.offset());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// ignoring
			e.printStackTrace();
		}
		var order = record.value();
		if(isFraud(order)) {
			System.out.println("Order suspected of fraud! Denied! ");
			orderDispacher.send("ECOMMERCE_ORDER_REJECTED", order.getUserId(), order);
		} else {
			System.out.println("Approved: " + order);
			orderDispacher.send("ECOMMERCE_ORDER_APPROVED", order.getUserId(), order);

		}
	}

	private static boolean isFraud(Order order) {
		return order.getAmount().compareTo(new BigDecimal("4500")) >= 0;
	}

}
