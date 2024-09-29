package org.example.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId;
	private Long quantity;

	@Version // NOTE: Spring이 아닌 javax.persistence 패키지의 어노테이션을 사용해야 함
	private Long version;

	public Stock() {
	}

	public Stock(Long productId, Long quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void decrease(Long quantity) {
		if (this.quantity - quantity < 0) {
			throw new IllegalArgumentException("재고는 0개 미만이 될 수 없습니다.");
		}

		this.quantity -= quantity;
	}
}
