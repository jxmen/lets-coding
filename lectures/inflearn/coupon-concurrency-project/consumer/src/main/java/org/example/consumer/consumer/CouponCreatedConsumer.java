package org.example.consumer.consumer;

import org.example.consumer.domain.Coupon;
import org.example.consumer.domain.FailedEvent;
import org.example.consumer.repository.CouponRepository;
import org.example.consumer.repository.FailedEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {
	private static final Logger logger = LoggerFactory.getLogger(CouponCreatedConsumer.class);

	private final CouponRepository couponRepository;
	private final FailedEventRepository failedEventRepository;

	public CouponCreatedConsumer(CouponRepository couponRepository, FailedEventRepository failedEventRepository) {
		this.couponRepository = couponRepository;
		this.failedEventRepository = failedEventRepository;
	}

	@KafkaListener(topics = "coupon_create", groupId = "group_1")
	public void listener(Long userId) {
		try {
			couponRepository.save(new Coupon(userId));
		} catch (Exception e) {
			logger.error("쿠폰 발급에 실패했습니다. userId={}", userId, e);
			failedEventRepository.save(new FailedEvent(userId));
		}
	}
}
