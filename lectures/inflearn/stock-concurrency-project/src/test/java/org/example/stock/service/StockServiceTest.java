package org.example.stock.service;

import org.example.stock.domain.Stock;
import org.example.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

	@Autowired
	private StockService stockService;

	@Autowired
	private StockRepository stockRepository;

	@BeforeEach
	void setUp() {
		Stock stock = new Stock(1L, 100L);
		stockRepository.saveAndFlush(stock);
	}

	@AfterEach
	void tearDown() {
		stockRepository.deleteAll();
	}

	@Test
	void 재고감소() {
		stockService.decrease(1L, 1L);

		// 100 - 1 = 99
		Stock stock = stockRepository.findById(1L).orElseThrow();

		assertEquals(99L, stock.getQuantity());
	}

	@Test
	void 재고감소_동시에_100개요청() throws InterruptedException {
		int threadCount = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(32);

		// 100개의 요청이 끝날때까지 기다려야 하므로, CountDownLatch를 사용한다.
		// NOTE: CountDownLatch는 다른 스레드에서 수행 중인 작업이 끝날때까지 대기할 수 있도록 도와주는 클래스이다.
		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {

				executorService.submit(() -> {
					try {
						stockService.decrease(1L, 1L);
					} finally {
						latch.countDown();
					}
				});

		}

		latch.await();

		Stock stock = stockRepository.findById(1L).orElseThrow();
		// 100 - (1 * 100) = 0

		assertEquals(0, stock.getQuantity());
	}
}
