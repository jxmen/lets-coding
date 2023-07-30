package factory_method.store;

import factory_method.factory.ChicagoStylePizzaFactory;
import factory_method.factory.PizzaFactory;
import factory_method.pizza.Pizza;

public class ChicagoSytlePizzaStore extends PizzaStore {

	private static final PizzaFactory factory;

	static {
		factory = new ChicagoStylePizzaFactory();
	}

	@Override
	protected Pizza createPizza(String type) {
		return factory.create(type);
	}
}
