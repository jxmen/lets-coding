package two_pointer_and_sliding_window_03._05;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		int n = in.nextInt();
		System.out.println(T.solution(n));
	}

	/**
	 * 최적화한 새로운 답
	 */
	private int solution(int n) {
		int answer = 0, sum = 0, lt = 0;

		int m = (n / 2) + 1;

		int[] arr = new int[m];
		for (int i = 0; i < m; i++) {
			arr[i] = i + 1;
		}

		for (int rt = 0; rt < m; rt++) {
			sum += arr[rt];
			if (sum == n) {
				answer++;
			}

			while (sum >= n) {
				sum -= arr[lt++];
				if (sum == n) {
					answer++;
				}
			}
		}

		return answer;
	}

	/**
	 * 처음에 푼 방식. 1~n까지 탐색해야 해서 비효율적
	 */
	private int solution2(int n) {
		int answer = 0;

		for (int lt = 1; lt < (n / 2) + 1; lt++) {
			int sum = 0, rt = lt;

			while (sum < n) {
				sum += rt++;
			}

			if (sum == n) {
				answer++;
			}
		}


		return answer;
	}
}
