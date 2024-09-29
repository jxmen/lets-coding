package org.example.stock.repository;

import org.example.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// NOTE: 실무에서 사용 시 데이터 소스를 분리해서 사용하는 것을 권장
public interface LockRepository extends JpaRepository<Stock, Long> {
	@Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
	void getLock(String key);

	@Query(value = "select release_lock(:key)", nativeQuery = true)
	void releaseLock(String key);
}
