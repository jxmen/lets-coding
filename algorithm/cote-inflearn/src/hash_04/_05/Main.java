package hash_04._05;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

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

		System.out.print(T.solution(n, k, ints));
	}

	private int solution(int n, int k, int[] ints) {
		TreeSet<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());
		int answer = -1;

		// treeSet에 값을 추가한다. 문제가 3개의 숫자의 합이므로 3중 for문을 사용한다.
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int l = j + 1; l < n; l++) {
					treeSet.add(ints[i] + ints[j] + ints[l]);
				}
			}
		}

		int cnt = 0;
		for (int x : treeSet) {
			cnt++;
			if (cnt == k) {
				return x;
			}
		}

		return answer;
	}


	private int solution2(int n, int k, int[] ints) {
		TreeSet<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());
		int answer = 0;

		for (int x : Arrays.stream(ints).toArray()) {
			treeSet.add(x);
		}

		for (int i = 0; i < 2; i++) {
			Integer last = treeSet.last();
			treeSet.remove(last);

			answer += last;
		}

		for (int i = 0; i < k - 1; i++) {
			treeSet.remove(treeSet.last());
		}

		answer += treeSet.last();

		return answer;
	}
}
