package item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class OnlyOneFieldHashCodeTest {

	@Test
	@DisplayName("hashCode()가 age 필드만 사용하면 컬렉션에서 문제 발생")
	void test() {
		// given
		Set<Person> personSet = new HashSet<>();

		Person person1 = new Person("test", 1);
		Person person2 = new Person("test", 1);

		// when
		personSet.add(person1);

		// then - person1만 추가했는데 person2 호출시 true가 나온다!!!
		assert person1.hashCode() == person2.hashCode();
		assert personSet.contains(person2);
	}

	record Person(String name, int age) {

		@Override
		public int hashCode() {
			// 잘못된 구현. age 필드만 사용하면 같은 나이를 가진 객체는 같은 해시코드를 반환한다.
			return age;
		}
	}
}


