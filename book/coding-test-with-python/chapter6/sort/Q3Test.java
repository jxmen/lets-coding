package chapter6.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


class Student {
	String name;
	int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}


	int getScore() {
		return this.score;
	}

}


public class Q3Test {

	public static void main(String[] args) throws Exception {
		Student student1 = new Student("홍길동", 77);
		Student student2 = new Student("이순신", 92);

		Q3Test main = new Q3Test();
		List<Student> solution = main.solution(Arrays.asList(student1, student2));

		if (!solution.equals(Arrays.asList(student2, student1))) {
			throw new Exception();
		}
	}

	private List<Student> solution(List<Student> list) {
		Comparator<? super Student> c = (Comparator<Student>) (o1, o2) -> o2.getScore() - o1.getScore();
		list.sort(c);

		return list;
	}


}
