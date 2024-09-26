package org.example.stock.service;


import org.example.stock.domain.Stock;
import org.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

	private final StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	// NOTE: Transactional을 그대로 사용하면, Spring이 생성하는 메서드 동작방식에 따라 트랜잭션이 커밋되기 직전에 다른 스레드가 값을 읽을 수 있다.
//	@Transactional
	public synchronized void decrease(Long id, Long quantity) {
		Stock stock = stockRepository.findById(id).orElseThrow();
		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}
