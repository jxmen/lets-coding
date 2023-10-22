package stack_and_queue_05._04;

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
		Stack<Integer> stack = new Stack<>();
		for (char x : input1.toCharArray()) {

			// 연산자일 경우 stack에서 값을 꺼내고, 연산을 진행한다.
			if (!isOperator(x)) {
				stack.push(Integer.parseInt(String.valueOf(x)));
				continue;
			}

			int rt = stack.pop();
			int lt = stack.pop();

			int valueFromOperator = getValueFromOperator(lt, rt, x);
			stack.push(valueFromOperator);
		}

		// 스택 마지막 값에 최종 연산된 값이 있으므로, pop한 결과를 리턴한다.
		return stack.pop();
	}

	private int getValueFromOperator(int lt, int rt, char x) {
		if (x == '+') {
			return lt + rt;
		}
		if (x == '-') {
			return lt - rt;
		}
		if (x == '*') {
			return lt * rt;
		}
		if (x == '/') {
			return lt / rt;
		}

		throw new IllegalStateException("주어진 값이 연산자가 아닙니다: " + x);
	}

	private boolean isOperator(char x) {
		if (x == '+' || x == '-' || x == '*' || x == '/') {
			return true;
		}

		return false;
	}
}
