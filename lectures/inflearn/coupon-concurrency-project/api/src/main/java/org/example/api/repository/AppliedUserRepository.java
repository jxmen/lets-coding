package org.example.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppliedUserRepository {
	public static final String APPLIED_USERS_KEY = "applied_users";

	private final RedisTemplate<String, String> redisTemplate;

	public AppliedUserRepository(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public Long add(Long userId) {
		return redisTemplate.opsForSet()
				.add(APPLIED_USERS_KEY, userId.toString());
	}
}
