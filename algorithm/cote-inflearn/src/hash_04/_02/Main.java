package hash_04._02;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 아나그램(해쉬)
 */
public class Main {

	public static void main(String[] args) {
		Main T = new Main();
		Scanner s = new Scanner(System.in);

		String str1 = s.next();
		String str2 = s.next();

		assert str1.length() == str2.length();
		assert str1.length() < 100;

		String result = T.solution(str1, str2);
		System.out.println(result);
	}

	/**
	 * 1. 맵을 하나만 만든다.
	 * 2. 첫번째 str에서는 value에 숫자를 증가시킨다.
	 * 3. 두번째 str을 돌 때는 value의 숫자를 감소시킨다.
	 * 4. 없는 키 값이 있는 경우 "NO"를 반환한다.
	 * 5. 숫자 값을 감소시킬려고 했을때, value가 이미 0인 경우 "NO"를 반환한다.
	 * 6. 그 외의 경우에는 "YES"를 반환한다.
	 */
	private String solution(String str1, String str2) {
		// 1. 맵을 하나만 만든다.
		Map<Character, Integer> map = new HashMap<>();

		// 2. 첫번째 str에서는 value에 숫자를 증가시킨다.
		for (char key : str1.toCharArray()) {
			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		// 3. 두번째 str을 돌 때는 value의 숫자를 감소시킨다.
		for (char key : str2.toCharArray()) {

			// 4. 없는 키 값이 있는 경우 "NO"를 반환한다.
			// 5. 숫자 값을 감소시킬려고 했을때, value가 이미 0인 경우 "NO"를 반환한다.
			if (!map.containsKey(key) || map.get(key) == 0) {
				return "NO";
			}

			map.put(key, map.get(key) - 1);
		}

		// 6. 그 외의 경우에는 "YES"를 반환한다.
		return "YES";
	}

	/**
	 * 처음 구현한 코드. 단순히 map을 2개를 사용하여 값을 집어넣고, 돌면서 비교한다.
	 */
	private String solution2(String str1, String str2) {
		Map<Character, Integer> map = new HashMap<>();
		Map<Character, Integer> map2 = new HashMap<>();

		for (char x : str1.toCharArray()) {
			map.put(x, map.getOrDefault(x, 0) + 1);
		}
		for (char x : str2.toCharArray()) {
			map2.put(x, map2.getOrDefault(x, 0) + 1);
		}

		for (char x : map.keySet()) {
			if (!map.get(x).equals(map2.get(x))) {
				return "NO";
			}
		}

		return "YES";
	}
}
