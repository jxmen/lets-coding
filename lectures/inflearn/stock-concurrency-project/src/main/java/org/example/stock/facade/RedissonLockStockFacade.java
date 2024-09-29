package org.example.stock.facade;

import org.example.stock.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RedissonLockStockFacade.class);

	private final RedissonClient redissonClient;
	private final StockService stockService;

	public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
		this.redissonClient = redissonClient;
		this.stockService = stockService;
	}

	public void decrease(Long id, Long quantity) {
		RLock lock = redissonClient.getLock(id.toString());

		try {
			// 락 획득 시도 및 점유 시간 설정
			boolean available = lock.tryLock(
					10, // 락 획득 시도 시간
					1, // 락 점유 시간
					TimeUnit.SECONDS
			);
			if (!available) {
				logger.warn("락 획득 실패");
				return;
			}

			// 락 획득 성공 시 재고 감소 로직 수행
			stockService.decrease(id, quantity);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {

			// 락 해제
			lock.unlock();
		}
	}
}
