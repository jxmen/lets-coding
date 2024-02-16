package org.example.springexamples.event;

public class MyEvent {

	private final Object source;
	private final int number;

	public MyEvent(Object source, int number) {
		this.source = source;
		this.number = number;
	}

	public Object getSource() {
		return source;
	}

	public int getNumber() {
		return number;
	}
}
