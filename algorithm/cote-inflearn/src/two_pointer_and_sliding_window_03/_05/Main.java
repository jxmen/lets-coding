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
	 * TODO: 이 방법으로 풀어도 괜찮을지...
	 */
	private int solution(int n) {
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
