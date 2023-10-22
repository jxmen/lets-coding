package dfs_and_bfs_begin_07._01;

public class Main {

	public static void main(String[] args) {
		Main T = new Main();

		T.dfs(3);
	}

	private void dfs(int n) {
		if (n == 0) {
			return;
		} else {
			dfs(n - 1); // 스택프레임이 여기서 멈춘 채로 새 함수를 스택 프레임에 쌓는다.
			System.out.print(n + " ");
		}
	}
}
