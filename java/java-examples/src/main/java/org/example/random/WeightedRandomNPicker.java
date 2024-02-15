package org.example.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WeightedRandomNPicker {
	private static final Random random = new Random();

	private WeightedRandomNPicker() {
		throw new IllegalStateException("Utility class");
	}

	public static <T> List<T> pickNRandomWithWeight(T[] elements, double[] weights, int n) {
		double total = Arrays.stream(weights)
				.reduce(0.0, Double::sum);

		for (int i = 0; i < weights.length; i++) {
			weights[i] /= total;
		}

		return pickNRandomWithWeight2(elements, weights, n);
	}


	public static <T> List<T> pickNRandomWithWeight2(T[] elements, double[] weights, int n) {
		if (elements.length != weights.length) {
			throw new IllegalArgumentException("Elements and weights must have the same length");
		}

		double totalWeight = 0.0;
		for (double weight : weights) {
			totalWeight += weight;
		}

		List<T> pickedElements = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			double randomValue = random.nextDouble() * totalWeight;

			for (int j = 0; j < elements.length; j++) {
				randomValue -= weights[j];
				if (randomValue <= 0.0 && !pickedElements.contains(elements[j])) {
					pickedElements.add(elements[j]);
					break;
				}
			}
		}

		if (pickedElements.size() != n) {
			// 요소를 충분히 선택하지 못한 경우 예외 처리
			throw new IllegalStateException("Could not pick enough elements");
		}

		return pickedElements;
	}
}
