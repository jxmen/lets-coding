package org.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponCountRepository {
	public static final String COUNT_KEY = "coupon_count";

	private final RedisTemplate<String, String> redisTemplate;

	public CouponCountRepository(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public Long increment() {
		return redisTemplate
				.opsForValue()
				.increment(COUNT_KEY);
	}
}
