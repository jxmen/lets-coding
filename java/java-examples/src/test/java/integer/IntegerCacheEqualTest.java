package integer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class IntegerCacheEqualTest {

	@ParameterizedTest
	@ValueSource(ints = { 127, -127 })
	void _127_Integer는_비교시_true를_반환한다(int value) {
		Integer a = value;
		Integer b = value;

		assert a == b;
	}
}
