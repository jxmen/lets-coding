package org.example.stock.facade;

import org.example.stock.repository.RedisLockRepository;
import org.example.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

	private final RedisLockRepository redisLockRepository;
	private final StockService stockService;

	public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
		this.redisLockRepository = redisLockRepository;
		this.stockService = stockService;
	}

	public void decrease(Long id, Long quantity) throws InterruptedException {
		while(!redisLockRepository.lock(id)) {
			// redis 부하를 조금 줄이기 위해 잠시 대기
			Thread.sleep(100);
		}

		// 락 획득에 성공했다면, 재고를 감소시킨다.
		try {
			stockService.decrease(id, quantity);
		} finally {
			redisLockRepository.unlock(id);
		}
	}
}
