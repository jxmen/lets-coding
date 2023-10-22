package hash_04._04;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
	Main T = new Main();
    String s = in.next();
    String t = in.next();

    System.out.print(T.solution(s, t));
  }

  /**
   * 처음에 tMap이라는 <Char, Integer> 형태의 Map을 먼저 만들고, equals로 맵에 있는 key,value가 동일한지 검사한다.
   *
   * hash + sliding window
   *
   * @param s 전체 문자열
   * @param t 아나그램인지 판별할 문자열
   *
   * @return 아나그램 개수
   */
  private int solution(String s, String t) {
    HashMap<Character, Integer> sMap = new HashMap<>();
    HashMap<Character, Integer> tMap = new HashMap<>();

    for (int i = 0; i < t.length(); i++) {
      sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
      tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
    }

    int sum = 0;
    if (sMap.equals(tMap)) {
      sum++;
    }

    // t~s까지 돌면서 하나 추가/삭제 하면서 같은지 검사한다.
    for (int i = t.length(); i < s.length(); i++) {
      sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);

      char outRangeValue = s.charAt(i - t.length());
      sMap.put(outRangeValue, sMap.get(outRangeValue) - 1);
      if (sMap.get(outRangeValue) == 0) {
        sMap.remove(outRangeValue);
      }

      if (sMap.equals(tMap)) {
        sum++;
      }
    }

    return sum;
  }

  /**
   * 처음 구현하려던 방식. map에 key를 넣으면서 비교하나, 아나그램인지 구분하는 코드가 빠져있어 원하는 결과가 나오지 않는다.
   */
  private int solution2(String s, String t) {
    HashMap<String, Integer> map = new HashMap<>();

    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < t.length(); i++) {
      stringBuilder.append(s.charAt(i));
    }

    map.put(stringBuilder.toString(), 1);

    for (int i = t.length(); i < s.length(); i++) {
      stringBuilder.append(s.charAt(i));
      stringBuilder.deleteCharAt(0);

      map.put(stringBuilder.toString(), map.getOrDefault(stringBuilder.toString(), 0) + 1);
    }

    return map.getOrDefault(t, 0);
  }
}
