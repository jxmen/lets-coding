package org.example.pattern.strategy;

import org.junit.jupiter.api.Test;

class UnTestAbleCarTest {

	@Test
	void wins_매서드_테스트() {
		UnTestAbleCar car1 = new UnTestAbleCar();
		car1.race();

		UnTestAbleCar car2 = new UnTestAbleCar();
		car2.race();

		// race가 랜덤으로 값을 증가시키기 때문에, 테스트를 실행할 때마다 결과가 달라진다.
		assert car1.wins(car2);
	}
}
