package org.example.api.service;

import org.example.api.producer.CouponCreateProducer;
import org.example.api.repository.CouponCountRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
	private static final int MAX_COUPON_COUNT = 100;

	private final CouponCountRepository couponCountRepository;
	private final CouponCreateProducer couponCreateProducer;

	public ApplyService(CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
		this.couponCountRepository = couponCountRepository;
		this.couponCreateProducer = couponCreateProducer;
	}

	public void apply(Long userId) {
		Long count = couponCountRepository.increment();
		if (count > MAX_COUPON_COUNT) {
			return;
		}

		couponCreateProducer.create(userId);
	}
}
