package br.com.alura.ecommerce;


import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import br.com.alura.ecommerce.consumer.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

public class EmailService implements ConsumerService<String> {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		new ServiceRunner(EmailService::new).start(5);
	}

	public String getConsumerGroup() {
		return EmailService.class.getSimpleName();
	}
	public String getTopic() {
		return "ECOMMERCE_SEND_EMAIL";
	}
	public void parse(ConsumerRecord<String, Message<String>> record) {
		System.out.println("------------------------------------------");
		System.out.println("Send E-mail");
		System.out.println(record.key());
		System.out.println(record.value());
		System.out.println(record.partition());
		System.out.println(record.offset());
		try {
			// O sleep foi colocado apenas para simular o tempo de espera de processamento
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// ignoring
			e.printStackTrace();
		}
		System.out.println("E-mail sent");
	}
}
