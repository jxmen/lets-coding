package dfs_and_bfs_begin_07._03;

public class Main {
	public static void main(String[] args) {
		Main T = new Main();
		System.out.println(T.dfs(5));
		System.out.println(T.dfs(6));
	}

	private int dfs(int n) {
		if (n == 1) return 1;

		return n * dfs(n - 1);
	}
}
