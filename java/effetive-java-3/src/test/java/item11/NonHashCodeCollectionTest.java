package item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class NonHashCodeCollectionTest {

	@Nested
	@DisplayName("논리적으로 같은 객체가 다른 해시코드를 반환할 때")
	class NonHashCodePhoneNumberTest {

		@Test
		@DisplayName("해시 기반 컬렉션에서 null을 리턴한다.")
		void test() {
			Map<PhoneNumber, String> m = new HashMap<>();
			m.put(new PhoneNumber(707, 867, 5309), "제니");

			assert m.get(new PhoneNumber(707, 867, 5309)) == null;
		}
	}
}

class PhoneNumber {

	private final int i;
	private final int i1;
	private final int i2;

	public PhoneNumber(int i, int i1, int i2) {
		this.i=i;
		this.i1=i1;
		this.i2=i2;
	}

	// 해시코드 재정의하지 않음

}
