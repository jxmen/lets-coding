package org.example.api.service;

import org.example.api.domain.Coupon;
import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
	private static final int MAX_COUPON_COUNT = 100;

	private final CouponRepository couponRepository;
	private final CouponCountRepository couponCountRepository;

	public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
		this.couponRepository = couponRepository;
		this.couponCountRepository = couponCountRepository;
	}

	public void apply(Long userId) {
		Long count = couponCountRepository.increment();
		if (count > MAX_COUPON_COUNT) {
			return;
		}

		couponRepository.save(new Coupon(userId));
	}
}
