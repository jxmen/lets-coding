package stack_and_queue_05._02;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		String input1 = in.next();

		System.out.println(T.solution(input1));
	}

	/**
	 * 해설 내용대로 풀어보자.
	 * <p>
	 * 문자열과 여는 괄호가 있을 경우에는 스택에 넣고, 닫는 괄호를 만나면 여는 괄호를 만날때까지 stack에서 pop 시키자.
	 *
	 * @param input1 괄호가 문자가 포함된 문자열
	 */
	private String solution(String input1) {
		Stack<Character> stack = new Stack<>();

		for (char x : input1.toCharArray()) {
			// 문자열과 여는 괄호가 있을 경우에는 스택에 넣고, 닫는 괄호를 만나면 여는 괄호를 만날때까지 stack에서 pop 시키자.
			if (x == '(' || x != ')') {
				stack.push(x);
			}

			if (x == ')') {
				while (stack.pop() != '(');
			}
		}


		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stack.size(); i++) {
			sb.append(stack.get(i));
		}

		return sb.toString();
	}

	/**
	 * (Solved) 처음 구현한 내용. 괄호에 대한 stack push/pop을 처리하고 있는지 체크한다.
	 *
	 * @param input1 괄호가 문자가 포함된 문자열
	 */
	private String solution2(String input1) {
		Stack<Character> stack = new Stack<>();
		StringBuilder stringBuilder = new StringBuilder();

		for (char x : input1.toCharArray()) {
			if (x == '(') {
				stack.push(x);
				continue;
			}

			if (x == ')') {
				stack.pop();
				continue;
			}

			if (stack.isEmpty()) {
				stringBuilder.append(x);
			}

		}

		return stringBuilder.toString();
	}
}
