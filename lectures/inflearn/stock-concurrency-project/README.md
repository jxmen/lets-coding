# 재고시스템으로 알아보는 동시성이슈 해결방법 소스코드

인프런 강의 [재고시스템으로 알아보는 동시성이슈 해결방법](https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C)의 소스코드를 직접 작성하고 실행해보는 저장소입니다.

### 동시성 문제 테스트 코드 작성하기 - synchronized 키워드 사용

ExecutorService와 CountDownLatch 클래스를 활용하여 테스트 코드를 작성합니다.

- ExecutorService: 스레드 풀을 생성하고 관리하는 클래스
- CountDownLatch: 스레드가 종료될 때까지 대기하는 클래스

```Java
@Test
void test() throws InterruptedException {
    int threadCount = 100;
    ExecutorService executorService = Executors.newFixedThreadPool(32);
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
    // 검증 코드 작성
}
```

### synchronized 키워드를 사용해도 동시성 문제가 발생하는 이유는?

Spring의 `@Transactional` 어노테이션의 동작 방식으로 인해 동시성 문제가 발생할 수 있습니다.

- `@Transactional` 어노테이션은 메소드에 적용되어 있을 때, 메소드 전체를 트랜잭션으로 묶어줍니다.

다음과 같은 코드가 있습니다.

```Java
class StockService {
	
    @Transactional
    public synchronized void decrease(Long productId, Long quantity) {
        Stock stock = stockRepository.findByProductId(productId);
        stock.decrease(quantity);
        stockRepository.save(stock);
    }
}
```

실제 동작 예시는 다음과 같습니다.

```Java
class TrasactionStockService {
    private StockService stockService;
    
    public void decrease(Long productId, Long quantity) {
        startTransaction();
		
        stockService.decrease(productId, quantity); // 실제 메서드
		
        endTransaction(); // 이 시점에 데이터베이스에 업데이트하는데, 이전에 다른 스레드가 decrease 메서드에 접근할 수 있다!!!
    }
}
```

현재는 decrease 메서드만 synchronized로 되어있는데, 이로 인해 endTransaction() 메서드가 호출되기 전에 다른 스레드가 decrease 메서드에 접근할 수 있어 실제 값이 반영되기 전에 다른 스레드가 값을 읽어버릴 수 있습니다.

- 해결하는 여러 방법이 있지만, Transactional 어노테이션을 제거하는 방법으로도 우선 해결할 수 있습니다.


