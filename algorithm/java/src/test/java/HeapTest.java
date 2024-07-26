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

	@Test
	void taskPriorityTest() {
		PriorityQueue<Task> heap = new PriorityQueue<>();
		Task task1 = new Task(1, "task1");
		Task task2 = new Task(2, "task2");
		Task task3 = new Task(3, "task3");
		Task task4 = new Task(4, "task4");
		Task task5 = new Task(5, "task5");
		heap.offer(task3);
		heap.offer(task1);
		heap.offer(task2);
		heap.offer(task4);
		heap.offer(task5);

		List.of(task5, task4, task3, task2, task1).forEach(task -> {
			System.out.println(task.name + " (우선순위: " + task.priority + ")");
			assert Objects.equals(heap.poll(), task);
		});
	}
}
