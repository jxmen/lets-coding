package hash_04._01;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public char solution(String s) {
		char answer = ' ';
		Map<Character, Integer> map = new HashMap<>();

		for (char x : s.toCharArray()) {
			/**
			 if (map.has(x)) {
			 map.put(x, map.get(x) + 1);
			 } else {
			 map.put(x, 0);
			 }
			 **/
			map.put(x, map.getOrDefault(x, 0)+1); // getOrDefault: 없을 경우 기본값을 설정한다.
		}

		int max = Integer.MIN_VALUE;
		for (char key : map.keySet()) {
			if (map.get(key) > max) {
				max = map.get(key);
				answer = key;
			}
		}

		return answer;
	}

	public static void main(String[] args){
		Main T = new Main();
		Scanner in=new Scanner(System.in);
		int input1 = in.nextInt();
		String str = in.next();

		if (str.length() != input1) {
			throw new RuntimeException("글자 수가 일치하지 않습니다.");
		}

		System.out.println(T.solution(str));
	}
}
