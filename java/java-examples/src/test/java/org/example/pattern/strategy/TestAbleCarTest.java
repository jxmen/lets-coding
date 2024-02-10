package org.example.pattern.strategy;

import org.junit.jupiter.api.Test;

class TestAbleCarTest {

	@Test
	void wins_매서드_테스트() {
		TestAbleCar car1 = new TestAbleCar();
		car1.race(() -> 4);

		TestAbleCar car2 = new TestAbleCar();
		car2.race(() -> 3);

		assert car1.wins(car2);
	}

}
