package dfs_and_bfs_begin_07._08;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();

		Scanner scanner = new Scanner(System.in);
		int s = scanner.nextInt();
		int e = scanner.nextInt();

		System.out.println(main.bfs(s, e));
	}

	public static void main2(String[] args) {
		Main main = new Main();

		System.out.println(main.bfs(8, 3));
	}

	private int bfs(int s, int e) {
		Queue<Integer> queue = new LinkedList<>();
		Map<Integer, Boolean> map = new HashMap<>();
		queue.offer(s);
		map.put(s, true);

		int level = 0;
		while (!queue.isEmpty()) {
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				Integer p = queue.poll();

				// 큐에서 뺀 값에서, +1, -1, +5한 값 중 원하는 값이 있을 경우 탈출한다.
				if ((p + 1) == e || (p - 1) == e || (p + 5) == e) {
					return ++level;
				}

				// +1, -1, +5한 값을 큐에 넣는다. 단, 큐에 이미 들어갔을 경우 건너뛴다.
				if (!map.containsKey(p + 1)) {
					queue.offer(p + 1);
					map.put(p + 1, true);
				}

				if (!map.containsKey(p + 5)) {
					queue.offer(p + 5);
					map.put(p + 5, true);
				}

				if (!map.containsKey(p - 1)) {
					queue.offer(p - 1);
					map.put(p - 1, true);
				}
			}

			level++;
		}

		return level + 1;
	}
}
