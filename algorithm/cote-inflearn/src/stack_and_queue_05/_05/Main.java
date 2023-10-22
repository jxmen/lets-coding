package stack_and_queue_05._05;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		String input1 = in.next();

		System.out.print(T.solution(input1));
	}

	private int solution(String input1) {
		Stack<Character> stack = new Stack<>();
		int answer = 0;

		// 여는 괄호 - 막대기의 시작, 닫는 괄호 - 막대기의 끝

		char prev = ' ';
		for (char x : input1.toCharArray()) {
			if (x == '(') {
				stack.push(x);
				prev = x;
				continue;
			}

			// '전의 값'이 여는 괄호일 경우 레이저이다.

			stack.pop();
			if (prev == '(') {
				// 레이저
				answer += stack.size();
			} else {
				answer++;
			}

			prev = ')';
		}

		return answer;
	}
}
