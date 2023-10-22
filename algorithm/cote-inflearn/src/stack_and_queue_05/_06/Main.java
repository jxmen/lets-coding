package stack_and_queue_05._06;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		int n = in.nextInt();
		int k = in.nextInt();

		System.out.print(T.solution(n, k));
	}

	private int solution(int n, int k) {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}

		while (queue.size() != 1) {
			for (int i = 0; i < k - 1; i++) {
				queue.offer(queue.poll());
			}

			queue.poll();
		}

		return queue.poll();
	}
}

