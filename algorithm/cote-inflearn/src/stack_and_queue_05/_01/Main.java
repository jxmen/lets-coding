package stack_and_queue_05._01;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		String input1 = in.next();

		System.out.print(T.solution(input1));
	}

	private String solution(String input1) {
		Stack<Character> stack = new Stack<>();

		for (char x : input1.toCharArray()) {
			if (x == '(') {
				stack.push(x);
			} else {
				if (stack.empty()) {
					return "NO";
				}

				stack.pop();
			}
		}

		if (stack.empty()) {
			return "YES";
		} else {
			return "NO";
		}
	}
}
