import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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
			assertThat(minHeap.poll()).isEqualTo(it);
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
			assertThat(maxHeap.poll()).isEqualTo(it);
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
			assertThat(heap.poll()).isEqualTo(task);
		});
	}

	@Test
	void kthValueTest() {
		int k = 3;
		int[] arr = {1, 5, 2, 9, 3, 7, 4, 6, 8};
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int num: arr) {
			minHeap.offer(num);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		assertThat(minHeap.peek()).isEqualTo(7);
	}

	@Test
	void heapStreamTest() {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		minHeap.offer(3);
		minHeap.offer(1);
		minHeap.offer(2);
		minHeap.offer(4);
		minHeap.offer(5);
		minHeap.forEach(System.out::println);

		List<Integer> minHeapStreamList = minHeap.stream()
				.mapToInt(Integer::intValue) // stream만 사용시에는 heap의 순서가 보장되지 않음
				.boxed()
				.toList();
		assertThat(minHeapStreamList).containsExactly(1, 3, 2, 4, 5);

		// sorted 사용시에도 정렬됨. 단 시간복잡도는 O(nlogn)
		List<Integer> minHeapSorted2 = minHeap.stream()
				.mapToInt(Integer::intValue)
				.boxed()
				.sorted() // O(nlogn)
				.toList();
		assertThat(minHeapSorted2).containsExactly(1, 2, 3, 4, 5);

		// poll 사용 시 순서 보장됨과 동시에 시간 복잡도 O(1) 소요 (전체는 O(N))
		List<Integer> minHeapSorted = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			minHeapSorted.add(minHeap.poll()); // O(1)
		}
		assertThat(minHeapSorted).containsExactly(1, 2, 3, 4, 5);
	}
}
