package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EmailServiceOrderApproved {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var emailServiceApproved = new EmailServiceOrderApproved();
        try (var service = new KafkaService<>(EmailServiceOrderApproved.class.getSimpleName(),
                "ECOMMERCE_ORDER_APPROVED",
                emailServiceApproved::parse,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Message<String>> record) {
        System.out.println("------------------------------------------");
        System.out.println("Sending approved order email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
    }
}
