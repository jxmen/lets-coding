package factory_method.store;

import factory_method.pizza.Pizza;
import factory_method.factory.NYStylePizzaFactory;
import factory_method.factory.PizzaFactory;

public class NyStylePizzaStore extends PizzaStore {
	private static final PizzaFactory factory = new NYStylePizzaFactory();

	@Override
	protected Pizza createPizza(String type) {
		return factory.create(type);
	}
}
