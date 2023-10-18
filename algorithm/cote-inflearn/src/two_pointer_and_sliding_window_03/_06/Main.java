package two_pointer_and_sliding_window_03._06;

import java.util.Scanner;


/**
 * 14 2
 * 1 1 0 0 1 1 0 1 1 0 1 1 0 1
 * <p>
 * <p>
 * lt와 rt가 처음에는 둘다 0인 곳으로 이동한다.
 * lt rt
 * 1 1 0 0 1 1 0 1 1 0 1 1 0 1
 * <p>
 * lt와 rt의 값을 1로 바꾼다.
 * <p>
 * lt rt
 * 1 1 1 1 1 1 0 1 1 0 1 1 0 1
 * => 현재 최대 길이: 6
 * <p>
 * // lt는 원래의 값은 0으로 바꾸고, lt는 이전 rt가 있는 곳으로 이동, rt는 이동 후 0을 1로 바꾼다.
 * lt    rt
 * 1 1 0 1 1 1 1 1 1 0 1 1 0 1
 * => 이때부터는 lt~rt + ?만큼만 계산한다.
 */

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		int n = in.nextInt();
		int k = in.nextInt();

		int[] ints = new int[n];
		for (int i = 0; i < n; i++) {
			ints[i] = in.nextInt();
		}

		System.out.print(T.solution2(n, k, ints));
	}

	/**
	 * 해설 답안. 수열의 원본을 건드리지 않고 0일 경우 숫자만 증가하는 식으로 진행한다.
	 *
	 * @param n    수열의 길이
	 * @param k    0을 1로 변경할 수 있는 숫자
	 * @param ints 수열
	 * @return 연속된 최대 수열의 길이
	 */
	private int solution2(int n, int k, int[] ints) {
		int answer = 0, cnt = 0, lt = 0;

		for (int rt = 0; rt < n; rt++) {
			if (ints[rt] == 0) {
				cnt++;
			}

			while (cnt > k) {
				if (ints[lt] == 0) {
					cnt--;
				}

				lt++;
			}

			answer = Math.max(answer, rt - lt + 1);
		}

		return answer;
	}



	/**
	 * 처음에 구현했던 방식... lt값을 적절히 증가시켜줘야 한다.
	 *
	 * @param n    수열의 길이
	 * @param k    0을 1로 변경할 수 있는 숫자
	 * @param ints 수열
	 * @return 연속된 최대 수열의 길이
	 */
	private int solution(int n, int k, int[] ints) {
		int answer = 0, lt = 0, rt = 0;

		// lt와 rt의 처음 이동할 곳을 찾는다. k개 만큼 숫자를 바꾼다.
		for (int i = 0; i < k; i++) {
			while (ints[rt] == 1) {
				ints[rt] = 1;
				rt++;
			}

			// rt가 처음 할당되었다면, lt에도 해당 값을 부여한다.
			if (i == 0) {
				lt = rt;
			}

			ints[rt] = 1;
		}

		// 처음에는 0부터 rt + ?까지의 길이를 최대값으로 저장한다.
		answer = rt - lt + 1;

		// ---- 처음 작업 끝

		while (ints[lt] == 1) {
			// lt의 값은 0으로 변경
			ints[lt] = 0;
			lt++; // TODO: 에러!! 이전에 0이었던 곳으로 이동해야 한다.

			while (rt < n && ints[rt] == 1) {
				// rt가 한칸 이동
				rt++;
			}

			if (rt == n) {
				break;
			}

			ints[rt] = 1;

			int sum = rt - lt + 1;
			if (sum > answer) {
				answer = sum;
			}

		}


		return answer;
	}

}
