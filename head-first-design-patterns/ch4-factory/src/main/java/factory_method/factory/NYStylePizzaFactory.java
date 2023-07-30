package factory_method.factory;

import factory_method.pizza.NYStylePizza;
import factory_method.pizza.Pizza;

public class NYStylePizzaFactory implements PizzaFactory {

	@Override
	public Pizza create(String type) {
		return new NYStylePizza();
	}
}
