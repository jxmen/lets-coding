package two_pointer_and_sliding_window_03._04;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main T = new Main();
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int m = s.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}

		System.out.print(T.solution(m, arr));
	}

	/**
	 * 직접 푼 문제
	 * <p>
	 * lt, rt 등의 가리키는 숫자값을 활용하자.
	 */
	private int solution(int m, int[] arr) {
		int answer = 0;

		// lt~rt까지 가리키는 값이 m일 경우 answer를 증가시킨다.
		int lt = 0, rt = 0;

		while (rt < arr.length) {
			int sum = 0;
			for (int i = lt; i <= rt; i++) {
				sum += arr[i];
			}

			// lt~rt까지의 값이 m보다 작을 경우, rt의 값을 증가시킨다.
			if (sum > m) {
				lt++;
			} else {
				if (sum == m) {
					answer++;
				}

				rt++;
			}
		}

		return answer;
	}

	/**
	 * 강의에서 제공하는 정답
	 */
	private int solution3(int m, int[] arr) {
		int answer = 0, sum = 0, lt = 0;
		for (int rt = 0; rt < arr.length; rt++) {
			sum += arr[rt];
			if (sum == m) {
				answer++;
			}
			while (sum >= m) {
				sum -= arr[lt++];
				if (sum == m) {
					answer++;
				}
			}
		}

		return answer;
	}

	/**
	 * 처음 sliding window 방식을 사용해서 2중 for문으로 풀었던 예제.
	 * 시간복잡도가 O(n^2)이라 Time Limit가 걸린다.
	 * <p>
	 * 이런 조건이 나오면 O(n^2)으로 풀면 안되겠다 라는걸 인지하자 !!!
	 * n (1<=n<=100000), m(1<=m<=100000000)
	 */
	private int solution2(int m, int[] arr) {
		int answer = 0;

		for (int i = 2; i < arr.length; i++) {
			int sum = 0;
			for (int _i = 0; _i < i; _i++) {
				sum += arr[_i];
			}

			if (sum == m) {
				answer++;
			}

			// 1 2 1 3 1 1 1 2
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				sum -= arr[j - i];

				if (sum == m) {
					answer++;
				}
			}
		}


		return answer;
	}
}
