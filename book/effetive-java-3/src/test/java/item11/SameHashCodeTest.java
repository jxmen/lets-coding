package item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SameHashCodeTest {

	@Test
	@DisplayName("hashCode()가 같은 객체를 비교")
	void test() {
		// given
		HashCodeSameObject obj1 = new HashCodeSameObject(1, "test");
		HashCodeSameObject obj2 = new HashCodeSameObject(1, "test");

		// when
		boolean result = obj1.hashCode() == obj2.hashCode();

		// then
		System.out.println("obj1.hashCode() = " + obj1.hashCode());
		System.out.println("obj2.hashCode() = " + obj2.hashCode());
		assert result;
	}

	private record HashCodeSameObject(int i, String test) {

		@Override
		public int hashCode() {
			int result = Integer.hashCode(this.i);
			result = 31 * result + this.test.hashCode();

			return result;
		}


		/**
		 * 성능이 큰 문제가 없을때만 Objects.hash 메서드를 사용하자.
		 */
		//		@Override
		//		public int hashCode2() {
		//			return Objects.hash(this.i, this.test);
		//		}
	}
}
