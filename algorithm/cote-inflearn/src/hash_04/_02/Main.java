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

		System.out.println(T.solution(str1, str2));
	}

	private String solution(String str1, String str2) {
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
