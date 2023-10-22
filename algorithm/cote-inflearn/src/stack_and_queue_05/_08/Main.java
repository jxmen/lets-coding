package stack_and_queue_05._08;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Person {
	private final int id;
	private final int priority;

	public Person(int id, int priority) {
		this.id = id;
		this.priority = priority;
	}

	public boolean hasMorePriority(Person poll) {
		return this.priority > poll.priority();
	}


	public boolean matchId(int m) {
		return this.id == m;
	}

	private int priority() {
		return this.priority;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		int n = in.nextInt();
		int m = in.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		System.out.print(T.solution(n, m, arr));
	}

	/**
	 * 해설을 참고하여 푼 방식.
	 * <p>
	 * 처음에는 id와 priority를 가지는 HashMap을 사용해볼까 했으나, Person 객체를 만들어서 queue에 담는 방식으로 해결한다.
	 *
	 * @param n 환자 길이
	 * @param m 몇번째 환자가 언제 검사를 받는지
	 * @param arr 환자 우선순위 목록
	 *
	 * @return m번째 환자가 받게되는 진료 순번
	 */
	private int solution(int n, int m, int[] arr) {
		Queue<Person> q = new LinkedList<>();
		int answer = 0;

		for (int i = 0; i < n; i++) {
			int x = arr[i];
			q.offer(new Person(i, x));
		}

		while (!q.isEmpty()) {
			Person poll = q.poll();

			// 우선순위가 더 높은 환자가 있을 경우 큐에 다시 집어넣는다.
			boolean qHasMorePriorityPerson = q.stream().anyMatch(p -> p.hasMorePriority(poll));
			if (qHasMorePriorityPerson) {
				q.offer(poll);
				continue;
			}

			answer++;
			if (poll.matchId(m)) {
				break;
			}
		}

		return answer;
	}

	/**
	 * 처음에 풀려던 방식. 같은 priority를 가지는 케이스를 고려하지 못했다.
	 */
	private int solution2(int n, int m, int[] arr) {
		int answer = 0;
		int target = arr[m];

		Queue<Integer> q = new LinkedList<>();
		for (int x : Arrays.stream(arr).toArray()) {
			q.offer(x);
		}

		while (!q.isEmpty()) {
			int poll = q.poll();
			if (q.stream().anyMatch(it -> poll > it)) {
				q.offer(poll);
				continue;
			}

			answer++;
			if (target == poll) { // 같은 값이 큐에 여러개 있을 수 있다.
				break;
			}
		}

		return answer;
	}
}
