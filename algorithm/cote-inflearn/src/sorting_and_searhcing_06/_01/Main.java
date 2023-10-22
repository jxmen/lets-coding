package sorting_and_searhcing_06._01;

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
		for (int i = 0; i < arr.length; i++) {
			// i에 있는 값이 가장 작을 경우 i를 바꿀 것이기 때문에 i로 설정한다.
			int changeToIndex = i;

			// j가 i뒤에 있는 값들을 돌면서 바꿀 인덱스가 있는지 탐색한다.
			for (int j = i + 1; j < arr.length; j++) {

				// j가 찾은 값이 더 작을 경우, 바꿀 인덱스를 j로 바꾼다.
				if (arr[j] < arr[changeToIndex]) {
					changeToIndex = j;
				}
			}

			// swap
			int temp = arr[changeToIndex];
			arr[changeToIndex] = arr[i];
			arr[i] = temp;
		}

		return arr;
	}
}
