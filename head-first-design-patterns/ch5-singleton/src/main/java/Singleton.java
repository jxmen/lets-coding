public class Singleton {
	/**
	 * Note: volatile은 멀티스레드 환경에서 CPU Cache가 아닌 Main Memory에서 값을 읽어들이겠다고 선언하는 것이다.
	 * 하나의 스레드가 write하고 나머지 스레드가 read하는 상황에서 쓰면 적합하다.
	 * 다만, 동시에 여러 스레드가 write하는 상황에서는 데이터의 원자성을 보장할 수 없다.
	 * <p>
	 * {@link} https://nesoy.github.io/articles/2018-06/Java-volatile
	 */
	private volatile static Singleton instance;

	private Singleton() {
	}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				// DCL (Double-Checked Locking)
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}

		return instance;
	}

	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();

		System.out.println("singleton1.equals(singleton2) = " + singleton1.equals(singleton2));
	}
}
