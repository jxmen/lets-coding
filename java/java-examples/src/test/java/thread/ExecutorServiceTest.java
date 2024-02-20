package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExecutorServiceTest {

	@Test
	void test() throws InterruptedException {
		int nThread = 30;
		ExecutorService executor = Executors.newFixedThreadPool(nThread);
		Counter counter = new Counter();

		for (int i = 0; i < nThread; i++) {
			for (int j = 0; j < 100; j++) {
				 executor.execute(() -> { counter.increase(); });
			}
		}

		Thread.sleep(1000); // 메인 스레드가 종료되지 않도록 대기한다.

		assertThat(counter.getCount()).isEqualTo(30 * 100);
	}

	static class Counter {
		private int count = 0;

		public synchronized void increase() {
			count++;
		}

		public int getCount() {
			return count;
		}
	}
}
