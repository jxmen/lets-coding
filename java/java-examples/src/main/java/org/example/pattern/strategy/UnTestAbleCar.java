package org.example.pattern.strategy;

import java.util.Random;

public class UnTestAbleCar {
	private static final Random RANDOM = new Random();

	private int score;

	public UnTestAbleCar() {
		this.score = 0;
	}

	public void race() {
		this.score += RANDOM.nextInt(10);
	}

	public boolean wins(UnTestAbleCar car2) {
		return this.getScore() > car2.getScore();
	}

	private int getScore() {
		return this.score;
	}
}
