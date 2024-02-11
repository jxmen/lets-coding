package org.example.pattern.decorator;

interface Component {
	String operation();
}

class RealComponent implements Component {

	@Override
	public String operation() {
		return "data";
	}
}

class MessageDecorator implements Component {

	private final Component component;

	public MessageDecorator(Component component) {
		this.component = component;
	}

	@Override
	public String operation() {
		return "*****" + this.component.operation() + "*****";
	}
}

public class Main {
	public static void main(String[] args) {
		Component realComponent = new RealComponent();
		String realComponentOperation = realComponent.operation();
		System.out.println("realComponentOperation = " + realComponentOperation);

		String messageDecoratorOperation = new MessageDecorator(realComponent).operation();
		System.out.println("messageDecoratorOperation = " + messageDecoratorOperation);

	}
}
