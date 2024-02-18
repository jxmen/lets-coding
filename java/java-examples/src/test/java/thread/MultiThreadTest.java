package thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

interface ThreadService<T> {
	void increaseCount();

	T getCount();
}

abstract class IntThreadService implements ThreadService<Integer> {

	protected int count;

	public IntThreadService(int count) {
		this.count = count;
	}

}

class IntNoThreadSafeService extends IntThreadService {

	public IntNoThreadSafeService(int count) {
		super(count);
	}

	@Override
	public void increaseCount() {
		this.count++;
	}

	@Override
	public Integer getCount() {
		return this.count;
	}
}

class IntThreadSafeService extends IntThreadService {

	public IntThreadSafeService(int count) {
		super(count);
	}

	@Override
	public void increaseCount() {
		synchronized (this) {
			this.count++;
		}
	}

	@Override
	public Integer getCount() {
		return this.count;
	}
}

class AtomicIntNoThreadSafeService implements ThreadService<AtomicInteger> {

	private final AtomicInteger count;

	public AtomicIntNoThreadSafeService(int count) {
		this(new AtomicInteger(count));
	}

	private AtomicIntNoThreadSafeService(AtomicInteger count) {
		this.count = count;
	}

	@Override
	public void increaseCount() {
		this.count.incrementAndGet();
	}

	@Override
	public AtomicInteger getCount() {
		return this.count;
	}
}

class VolatileIntThreadSafeService implements ThreadService<Integer> {

	private volatile int count;

	public VolatileIntThreadSafeService(int count) {
		this.count = count;
	}

	@Override
	public synchronized void increaseCount() {
		this.count = this.count + 1;
	}

	@Override
	public Integer getCount() {
		return this.count;
	}
}

public class MultiThreadTest {

	@Test
	void threadNotSafeTest() throws InterruptedException {
		int initCount = 0;
		ThreadService<Integer> service = new IntNoThreadSafeService(initCount);

		Thread[] threads = new Thread[10];
		defineThreadRunnable(threads, service);

		startThreads(threads);
		waitThreads(threads);

		int expectCount = initCount + (10 * 1000);
		assertThat(service.getCount()).isNotEqualTo(expectCount);
	}

	@Test
	void threadSafeTest() throws InterruptedException {
		int initCount = 0;
		IntThreadSafeService service = new IntThreadSafeService(initCount);

		Thread[] threads = new Thread[10];
		defineThreadRunnable(threads, service);

		startThreads(threads);
		waitThreads(threads);

		int expectCount = initCount + (10 * 1000);
		assertThat(service.getCount()).isEqualTo(expectCount);
	}

	@Test
	void atomicIntegerThreadSafeTest() throws InterruptedException {
		int initCount = 0;
		ThreadService<AtomicInteger> service = new AtomicIntNoThreadSafeService(initCount);

		Thread[] threads = new Thread[10];

		defineThreadRunnable(threads, service);

		startThreads(threads);
		waitThreads(threads);

		int expectCount = initCount + (10 * 1000);
		assertThat(service.getCount().get()).isEqualTo(expectCount);
	}

	@Test
	void volatileTest() throws InterruptedException {
		int initCount = 0;
		ThreadService<Integer> service = new VolatileIntThreadSafeService(initCount);

		Thread[] threads = new Thread[10];
		defineThreadRunnable(threads, service);

		startThreads(threads);
		waitThreads(threads);

		int expectCount = initCount + (10 * 1000);
		assertThat(service.getCount()).isEqualTo(expectCount);
	}

	private static void defineThreadRunnable(Thread[] threads, ThreadService<?> service) {
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					service.increaseCount();
				}
			});
		}
	}

	private static void waitThreads(Thread[] threads) throws InterruptedException {
		for (Thread thread : threads) {
			thread.join();
		}
	}

	private static void startThreads(Thread[] threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}
}
