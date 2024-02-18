package factory_method.factory;

import abstract_factory.NYPizzaIngredientFactory;
import abstract_factory.PizzaIngredientFactory;
import factory_method.pizza.ny.NYStyleCheesePizza;
import factory_method.pizza.ny.NYStylePizza;
import factory_method.pizza.Pizza;

public class NYStylePizzaFactory implements PizzaFactory {

	private static final PizzaIngredientFactory ingredientFactory;

	static {
		ingredientFactory = new NYPizzaIngredientFactory();
	}

	@Override
	public Pizza create(String type) {
		if ("치즈".equals(type)) {
			return new NYStyleCheesePizza(ingredientFactory);
		}

		return new NYStylePizza(ingredientFactory);
	}
}
