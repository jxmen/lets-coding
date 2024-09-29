package org.example.api.service;

import org.example.api.producer.CouponCreateProducer;
import org.example.api.repository.AppliedUserRepository;
import org.example.api.repository.CouponCountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
	private static final int MAX_COUPON_COUNT = 100;

	private static final Logger logger = LoggerFactory.getLogger(ApplyService.class);

	private final CouponCountRepository couponCountRepository;
	private final CouponCreateProducer couponCreateProducer;
	private final AppliedUserRepository appliedUserRepository;

	public ApplyService(CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
		this.couponCountRepository = couponCountRepository;
		this.couponCreateProducer = couponCreateProducer;
		this.appliedUserRepository = appliedUserRepository;
	}

	public void apply(Long userId) {
		Long apply = appliedUserRepository.add(userId);
		if (apply != 1) { // 이미 쿠폰 발급을 신청했다면
			logger.info("이미 쿠폰 발급을 신청하였습니다. userId={}", userId);
			return;
		}

		Long count = couponCountRepository.increment();
		if (count > MAX_COUPON_COUNT) {
			return;
		}

		couponCreateProducer.create(userId);
	}
}
