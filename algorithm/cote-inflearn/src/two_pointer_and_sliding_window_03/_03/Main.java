package two_pointer_and_sliding_window_03._03;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main T = new Main();
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int k = s.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}

		System.out.print(T.solution(n, k, arr));
	}

	private int solution(int n, int k, int[] arr) {
		int max = 0, sum = 0;

		// 1. 처음에 (0 ~ k-1) 까지의 값을 미리 할당한다.
		for (int i = 0; i < k; i++) {
			sum += arr[i];
		}
		max = sum;

		// 2. for문을 i=k부터 시작한다. 그리고 기존의 answer에서 현재 인덱스의 값을 추가하고, 이전 영역에 있는 값(i-k)을 뺀다.
		for (int i = k; i < n; i++) {
			sum += arr[i]; // 새 윈도우에 들어온 값을 더해준다.
			sum -= arr[i - k]; // 윈도우에서 벗어난 값을 빼준다.

			if (sum > max) {
				max = sum;
			}
		}


		return max;
	}
}
