package two_pointer_and_sliding_window_03._01;

import java.util.ArrayList;
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

	private List<Integer> solution2(int n, int m, int[] a, int[] b) {
		List<Integer> answer = new ArrayList<>();

		// 배열의 값 인덱스를 가리킬 two pointer 선언
		int p1 = 0, p2 = 0;

		while (p1 < n && p2 < m) {
			if (a[p1] < b[p2]) {
				answer.add(a[p1++]);
			} else {
				answer.add(b[p2++]);
			}
		}

		while(p1 < n) answer.add(a[p1++]);
		while(p2 < m) answer.add(b[p2++]);

		return answer;
	}

	private int[] solution(int n, int m, int[] a, int[] b) {
		List<Integer> answer = new ArrayList<>();

		// 배열의 값 인덱스를 가리킬 two pointer 선언
		int p1 = 0, p2 = 0;

		while (p1 < n && p2 < m) {
			if (a[p1] < b[p2]) {
				answer.add(a[p1]); // answer.add(a[p1++]) 이런식으로도 줄여서 쓸 수 있음 (후위 증감 연산자)
				p1++;
			} else {
				answer.add(b[p2]);
				p2++;
			}
		}

		if (p1 == n) {
			for (int i = p2; i < m; i++) {
				answer.add(b[i]);
			}
		} else {
			for (int i = p1; i < n; i++) {
				answer.add(a[n]);
			}
		}

		return toIntArray(answer);
	}

	private int[] toIntArray(List<Integer> answer) {
		int[] newInt = new int[answer.size()];

		for (int i = 0; i < answer.size(); i++) {
			newInt[i] = answer.get(i);
		}

		return newInt;
	}
}
