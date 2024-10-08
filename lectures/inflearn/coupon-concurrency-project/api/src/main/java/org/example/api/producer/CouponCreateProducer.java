package org.example.api.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateProducer {
	public static final String TOPIC = "coupon_create";

	private final KafkaTemplate<String, Long> kafkaTemplate;

	public CouponCreateProducer(KafkaTemplate<String, Long> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void create(Long userId) {
		kafkaTemplate.send(TOPIC, userId);
	}
}
