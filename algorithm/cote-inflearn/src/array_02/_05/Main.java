package array_02._05;


import java.util.Scanner;

/**
 * 소수 - 아리스토테네스 체
 * <p>
 * 자연수 N이 입력되면 1부터 N까지의 소수의 개수를 출력하는 프로그램을 작성하세요.
 * 만약 20이 입력되면 1부터 20까지의 소수는 2, 3, 5, 7, 11, 13, 17, 19로 총 8개입니다.
 * 첫 줄에 자연수의 개수 N(2<=N<=200,000)이 주어집니다.
 * 첫 줄에 소수의 개수를 출력합니다.
 */
public class Main {
	public static void main(String[] args) {
		Main T = new Main();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		System.out.println(T.solution(n));
	}

	private int solution(int n) {
		int answer = 0;

		int[] arr = new int[n + 1];
		for (int i = 2; i <= n; i++) {
			if (arr[i] == 0) {
				answer++;

				for (int j = i; j <= n; j=j+i) { // j의 배수들은 소수가 아님을 표시
					arr[j] = 1; // 소수가 아님
				}
			}
		}

		return answer;
	}
}
