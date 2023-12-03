package dfs_and_bfs_begin_07._06;

public class Main {

	static int n = 0;
	static int[] checks;

	public static void main(String[] args) {
		n = 3;
		checks = new int[n+1];

		Main.dfs(1);
	}

	private static void dfs(int L) {
		if (L == n + 1) {
			String tmp = "";
			for (int i = 1; i <= n; i++) {
				if (checks[i] == 1) {
					tmp += (i + " ");
				}
			}

			/**
			 * 공집합 출력 제외
			 */
			if (tmp.length() > 0) {
				System.out.println(tmp);
			}
		} else {
			checks[L] = 1; // 사용한다
			dfs(L + 1); // 왼쪽 이동
			checks[L] = 0; // 사용 안한다
			dfs(L + 1); // 오른쪽 이동
		}
	}

}
