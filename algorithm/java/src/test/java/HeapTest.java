import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

class HeapTest {

	@Test
	void minHeapTest() {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		minHeap.offer(3);
		minHeap.offer(1);
		minHeap.offer(2);
		minHeap.offer(4);
		minHeap.offer(5);

		List.of(1, 2, 3, 4, 5).forEach(it -> {
			assert Objects.equals(minHeap.poll(), it);
		});
	}

	@Test
	void maxHeapTest() {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

		maxHeap.offer(3);
		maxHeap.offer(1);
		maxHeap.offer(2);
		maxHeap.offer(4);
		maxHeap.offer(5);

		List.of(5, 4, 3, 2, 1).forEach(it -> {
			assert Objects.equals(maxHeap.poll(), it);
		});
	}
}
