package two_pointer_and_sliding_window_03._02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main T = new Main();
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = kb.nextInt();
		}

		int m = kb.nextInt();
		int[] b = new int[m];
		for (int i = 0; i < m; i++) {
			b[i] = kb.nextInt();
		}

		for (int x : T.solution(n, m, a, b)) {
			System.out.print(x + " ");
		}
	}

	private List<Integer> solution(int n, int m, int[] a, int[] b) {
		List<Integer> answer = new ArrayList<>();

		// two pointer 알고리즘을 하기 위해서는 오름차순 정렬을 미리 해야 함.
		Arrays.sort(a);
		Arrays.sort(b);

		int p1 = 0, p2 = 0;
		while (p1 < n && p2 < m) {
			if (a[p1] == b[p2]) {
				answer.add(a[p1]);
				p1++;
				p2++;
				continue;
			}

			if (a[p1] < b[p2]) {
				p1++;
			} else {
				p2++;
			}
		}

		return answer;
	}
}
