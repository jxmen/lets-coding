package stack_and_queue_05._03;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main T = new Main();
		int n = in.nextInt();

		int[][] ints = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ints[i][j] = in.nextInt();
			}
		}

		int m = in.nextInt();
		int[] moves = new int[m];
		for (int i = 0; i < m; i++) {
			moves[i] = in.nextInt();
		}

		System.out.print(T.solution(n, ints, m, moves));
	}

	/**
	 * @param n     보드 크기
	 * @param board n*n 형태의 보드
	 * @param m     움직일 개수
	 * @param moves 움직일 위치
	 * @return 인형이 터진 개수
	 */
	private int solution(int n, int[][] board, int m, int[] moves) {
		Stack<Integer> stack = new Stack<>();
		int answer = 0;

		for (int i = 0; i < m; i++) {
			int move = moves[i];
			// 인형을 꺼낼 위치 숫자 (1~n 중 하나)

			for (int j = 0; j < n; j++) {
				int boardValue = board[j][move - 1]; // 보드의 값. move의 경우 1부터 시작하므로 -1 해준다.
				if (boardValue == 0) {
					continue;
				}

				if (stack.isEmpty()) {
					stack.push(boardValue);
					board[j][move - 1] = 0;
					break;
				}

				// 스택 마지막 값이 같은 값이라면 스택에서 그냥 빼고, 카운트를 증가시킨다.
				// 인형을 꺼냈으므로 꺼낸 위치도 0으로 변경한다!!!
				if (stack.peek() == boardValue) {
					stack.pop();

					// stack에 같은 값이 있을 경우, 넣은 인형과 원래 있던 인형이 같이 터지는 것이므로 answer를 2개 증가시켜야 한다!!
					answer += 2; // <===== 인형이 터지는 것이므로 2개를 더해야 한다...
				} else { // 아닐 경우 스택에 그냥 추가한다.
					stack.push(boardValue);
				}

				board[j][move - 1] = 0;
				break;
			}
		}

		return answer;
	}
}
