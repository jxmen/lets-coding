import java.util.Objects;

class Task implements Comparable<Task> {
	int priority;
	String name;

	public Task(int priority, String name) {
		this.priority = priority;
		this.name = name;
	}

	@Override
	public int compareTo(Task other) {
		return other.priority - this.priority; // 높은 우선순위가 먼저
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Task task = (Task) o;
		return priority == task.priority && Objects.equals(name, task.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(priority, name);
	}
}
