package factory_method.store;

import factory_method.pizza.Pizza;

public abstract class PizzaStore {

	public Pizza orderPizza() {
		return this.orderPizza("기본");
	}

	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

	protected abstract Pizza createPizza(String type);
}
