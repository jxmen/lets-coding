package greedy_09._04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Lecture implements Comparable<Lecture> {

	public int money;
	public int date;

	public Lecture(int money, int date) {
		this.money = money;
		this.date = date;
	}

	@Override
	public int compareTo(Lecture ob) {
		return ob.date - this.date;
	}
}

public class Main {

	static int n, max = Integer.MAX_VALUE;

	public int solution(List<Lecture> arr) {
		int answer = 0;

		Collections.sort(arr);

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int j = 0;
		for (int i = max; i >= 1; i--) {
			for (; j < n; j++) {
				if (arr.get(j).date < i) break;
				pq.offer(arr.get(j).money);
			}

			if (!pq.isEmpty()) {
				answer = answer + pq.poll();
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		Main T = new Main();
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt();

		ArrayList<Lecture> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int m = kb.nextInt();
			int d = kb.nextInt();

			arr.add(new Lecture(m, d));
			if (d > max) {
				max = d;
			}
		}

		int solution = T.solution(arr);
		System.out.println(solution);
	}
}
