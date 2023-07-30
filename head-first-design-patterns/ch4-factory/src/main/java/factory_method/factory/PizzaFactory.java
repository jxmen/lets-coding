package factory_method.factory;

import factory_method.pizza.Pizza;

public interface PizzaFactory {
	Pizza create(String type);
}
