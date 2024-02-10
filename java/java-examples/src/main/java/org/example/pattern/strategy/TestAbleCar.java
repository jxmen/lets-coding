package org.example.pattern.strategy;

import java.util.Random;

public class TestAbleCar {
	private static final RandomNumerGenerator DEFAULT_RANDOM_NUMBER_GENERATOR;

	static{
		Random random = new Random();
		DEFAULT_RANDOM_NUMBER_GENERATOR = () -> random.nextInt(10);
	}

	private int score = 0;

	public void race() {
		this.race(DEFAULT_RANDOM_NUMBER_GENERATOR);
	}

	public void race(RandomNumerGenerator randomNumerGenerator) {
		this.score += randomNumerGenerator.generate();
	}

	public boolean wins(TestAbleCar other) {
		return this.getScore() > other.getScore();
	}

	private int getScore() {
		return this.score;
	}
}
