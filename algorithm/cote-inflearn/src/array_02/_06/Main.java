package array_02._06;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		int n = in.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		T.solution(arr);
	}

	private void solution(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i];
			if (isSosu(num)) {
				System.out.println(reversedSosu(num));
			}
		}
	}

	private boolean isSosu(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 019 같은 케이스는 19로 리턴해야 함
	 *
	 * @param num 뒤집을 숫자
	 * @return 뒤집은 소수
	 */
	private int reversedSosu(int num) {
		assert isSosu(num);

		// TODO: 구현하기
		return 0;
	}
}
