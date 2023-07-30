package factory_method.factory;

import factory_method.pizza.ChicagoPizza;
import factory_method.pizza.Pizza;

public class ChicagoStylePizzaFactory implements PizzaFactory {

	@Override
	public Pizza create(String type) {
		return new ChicagoPizza();
	}
}
