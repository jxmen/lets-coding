package stack_and_queue_05._07;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		String n = in.next();
		String k = in.next();

		System.out.print(T.solution(n, k));
	}

	private String solution(String n, String k) {
		Queue<Character> nq = new LinkedList<>();

		for (char x : n.toCharArray()) {
			nq.offer(x);
		}

		for (char x : k.toCharArray()) {
			if (nq.isEmpty()) {
				break;
			}

			if (!nq.contains(x)) {
				continue;
			}

			// nq.contains(k) == true
			Character poll = nq.poll();
			if (!Objects.equals(poll, x)) {
				return "NO";
			}
		}

		return nq.isEmpty() ? "YES" : "NO";
	}

	/**
	 * 처음 풀려던 방식. 큐를 2개 생성하고 맞을때까지 poll 하는 방식
	 *
	 * TLE이 걸려 다른 해답이 필요
	 */
	private String solution2(String n, String k) {
		Queue<Character> nq = new LinkedList<>();
		Queue<Character> kq = new LinkedList<>();

		for (char x : n.toCharArray()) {
			nq.offer(x);
		}
		for (char x : k.toCharArray()) {
			kq.offer(x);
		}

		while (!nq.isEmpty()) {
			Character nqPoll = nq.poll();
			Character kqPoll = kq.poll();

			if (Objects.equals(nqPoll, kqPoll)) {
				continue;
			}

			if (nq.contains(kqPoll)) {
				return "NO";
			}

			while (!Objects.equals(kq.peek(), nqPoll)) {
				Character poll = kq.poll();
				if (nq.contains(poll)) {
					return "NO";
				}
			}
			kq.poll();

		}

		return "YES";
	}
}

