package hash_04._03;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 매출액의 종류
 */
public class Main {

	public static void main(String[] args) {
		Main T = new Main();
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int k = s.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}

		for (int aInt : T.solution(n, k, arr)) {
			System.out.print(aInt + " ");
		}
	}

	/**
	 * 기존의 맵을 계속 재활용하는 방식으로 구현해본다.
	 * <p>
	 * 인덱스를 늘려가면서, 늘어나기 전 인덱스의 값은 지운다.
	 * <p>
	 * e.g. k=4, [20 12 20 10].size()
	 * => 이후 [12 20 10], 여기에 새 값을 추가하여 [12 20 10 23].size() 호출
	 */
	private List<Integer> solution(int n, int k, int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> answer = new ArrayList();

		for (int i = 0; i < (n - k + 1); i++) {

			// TODO: 인덱스를 밀면서, 새로 추가된 것들에 대해서만 count를 증가해야 한다. - sliding window
			for (int j = i; j < (k + i); j++) {
				map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
			}

			answer.add(map.size());
			Integer integer = map.get(arr[i]);
			map.put(arr[i], integer - 1);
			if (map.get(arr[i]) == 0) {
				map.remove(arr[i]);
			}
		}

		return answer;
	}

	/**
	 * 처음에 구현하려고 했던 코드. 입력이 들어올때마다 map을 만든다.
	 */
	private int solution2(int startIdx, int k, int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = startIdx; i < k + startIdx; i++) {
			Integer orDefault = map.getOrDefault(arr[i], 0);
			map.put(arr[i], orDefault + 1);
		}

		return map.size();
	}

}
