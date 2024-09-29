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

### synchronized 키워드의 문제점

synchornized는 하나의 프로세스에서만 유효합니다. 즉, 여러 대의 서버가 동작하고 있다면, synchronized 키워드는 동작하지 않습니다.

요즘은 대부분이 2대 이상인 서버에서 동작하므로 이 방법은 거의 사용하지 않습니다.

### MySQL에서 제공하는 방법으로 동시성 문제 해결하기

- 비관적 락
- 낙관적 락 
- 네임드 락

### 비관적 락 (Pessimistic Lock)

> 데이터에 실제로 락을 걸어 접근하지 못하도록 하는 방법

Spring에서 제공하는 `@Lock` 어노테이션을 사용하여 비관적 락을 사용할 수 있습니다.

```Java
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 락
	@Query("SELECT s FROM Stock s WHERE s.id = :id")
	Stock findByIdWithPessimisticLock(Long id);
}
```

실제 쿼리 실행시에는, 데이터 조회 쿼리에 `FOR UPDATE` 구문이 추가됩니다.

```SQL
select s1_0.id,s1_0.product_id,s1_0.quantity 
from stock s1_0 
where s1_0.id=? 
for update
```

### 낙관적 락 (Optimistic Lock)

> 실제로 락을 쓰지 않고 버전을 사용하여 데이터 정합성을 맞추는 방법.

별도의 예외 처리 로직을 추가해주어야 한다.

Jpa에서는 별도의 버전 필드를 사용하고, `@Version` 어노테이션을 사용하여 버전을 관리할 수 있습니다.

```Java

@Entity
public class Stock {

    @Version // javax.persistence
    private Long version;
}
```

버전이 변경되었다면 예외가 발생하므로 재시도 로직을 넣어주어야 합니다.

### 네임드 락 (Named Lock)

> 이름을 가진 메타데이터 락

- 이름을 가진 락 획득 후, 해제할 때 까지 다른 세션에서 접근할 수 없음
- 주의사항: **트랜잭션 종료 시 락이 자동으로 해제되지 않음.** 반드시 별도로 해제해주어야 함.
- MySQL은 get-lock 명령어를 통해 락 획득, release-lock 명령어를 통해 락 해제
- 커넥션 풀 부족 현상을 방지하기 위해, 실무에서는 데이터 소스를 분리해서 사용 권장
- 주로 `분산 락` 구현에 사용되며, 낙관적 락과 달리 timeout을 손쉽게 구현할 수 있다.

```Java
// NOTE: 실무에서 사용 시 데이터 소스를 분리해서 사용하는 것을 권장
public interface LockRepository extends JpaRepository<Stock, Long> {
	@Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
	void getLock(String key);

	@Query(value = "select release_lock(:key)", nativeQuery = true)
	void releaseLock(String key);
}
```

### Redis를 활용하는 방법 - Lettuce, Redisson

Lettuce
- MySQL Named Lock과 유사한 방식으로 구현
- setnx 명령어 사용 (set if not exist)
- spin lock 방식으로 구현된다.
  - 락을 획득할 수 있는지 계속해서 retry를 시도한다.
- 구현이 간단하나 레디스에 부하를 줄 수 있는 단점이 있다.

Redisson
- pub-sub 기반으로 채널을 만들어서, 락 획득/해제 여부를 채널에 알리는 방식이다.
- Redis의 부하는 적지만, 구현이 비교적 복잡하고 별도의 라이브러리가 필요하다.
