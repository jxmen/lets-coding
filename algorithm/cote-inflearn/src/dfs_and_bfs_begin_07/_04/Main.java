package dfs_and_bfs_begin_07._04;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Main T = new Main();
		int n = 45;

		for (int i = 1; i <= n; i++) {
			System.out.println(T.dfs(i));
		}
	}

	static Map<Integer, Integer> map = new HashMap<>();

	private int dfs(int n) {
		if (n < 0) throw new Error("잘못된 숫자");

		if (n == 1) return 1;
		if (n == 2) return 2;

		if (map.containsKey(n)) return map.get(n);

		int sum = dfs(n - 1) + dfs(n - 2);
		map.put(n, sum);

		return sum;
	}
}
