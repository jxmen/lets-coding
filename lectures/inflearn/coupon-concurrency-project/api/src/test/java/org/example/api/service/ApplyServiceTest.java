package org.example.api.service;

import org.example.api.repository.CouponCountRepository;
import org.example.api.repository.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@BeforeEach
	void setUp() {
		// 쿠폰 개수 초기화
		redisTemplate.delete(CouponCountRepository.COUNT_KEY);
	}

	@Test
	void 한번만_응모() {
		applyService.apply(1L);

		long count = couponRepository.count();

		assertThat(count).isEqualTo(1);
	}

	@Test
	void 여러명_응모해도_100개만_쿠폰이_발급되어야한다() throws InterruptedException {
		int threadCount = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					applyService.apply(1L);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		long count = couponRepository.count();
		assertThat(count).isEqualTo(100);
	}
}
