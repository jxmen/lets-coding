package dfs_and_bfs_begin_07._02;

/**
 * 재귀함수로 2진수 출력하기
 */
public class Main {

	public static void main(String[] args) {
		Main T = new Main();

		T.dfs(11); // 11의 이진수 1011이 출력되어야 함
	}

	private void dfs(int n) {
		if (n == 0) {
			return;
		}

		dfs(n / 2);
		System.out.print(n % 2);
	}

}
