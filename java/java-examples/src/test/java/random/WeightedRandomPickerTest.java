package random;

import org.example.random.WeightedRandomNPicker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class WeightedRandomPickerTest {

	@RepeatedTest(1000)
	@DisplayName("가중치가 있는 요소들 중 n개를 랜덤하게 선택한다.")
	void testPickRandomWithWeight() {
		String[] elements = {"A", "B", "C", "D", "E"};
		double[] weights = {0, 0, 0, 0, 1};
		int n = 1; // 뽑을 요소의 개수

		List<String> result = WeightedRandomNPicker.pickNRandomWithWeight(elements, weights, n);

		assertThat(result).hasSize(n);
		assertThat(result.get(0)).isEqualTo("E");
	}
}


