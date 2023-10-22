package sorting_and_searhcing_06._02;

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

		for (int x : T.solution(arr)) {
			System.out.print(x + " ");
		}
	}

	private int[] solution(int[] arr) {
		int n = arr.length;

		// n-1 만큼의 크기만 반복한다. n일 경우에는 이미 정렬이 되어있다.
		for (int i = 0; i < (n - 1); i++) {

			// j는 i의 크기만큼 점점 감소한다. 맨 뒤의 값은 이미 정렬이 되어있는 상태이기 때문이다.
			for (int j = 0; j < (n - i - 1); j++) {

				// j가 n-i-1 만큼 도므로, 그 뒤의 값을 비교해서 작을경우 swap한다.
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}

		return arr;
	}

	private void swap(int[] arr, int j, int i) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}
