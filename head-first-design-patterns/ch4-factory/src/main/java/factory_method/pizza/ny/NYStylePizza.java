package factory_method.pizza.ny;

import abstract_factory.PizzaIngredientFactory;
import factory_method.pizza.Pizza;

public class NYStylePizza extends Pizza {
	private final PizzaIngredientFactory ingredientFactory;

	public NYStylePizza(PizzaIngredientFactory pizzaIngredientFactory) {
		this.ingredientFactory = pizzaIngredientFactory;
	}

	@Override
	public void prepare() {
		dough = this.ingredientFactory.createDough();
		sauce = this.ingredientFactory.createSauce();
		veggies = this.ingredientFactory.createVeggies();
		cheese = this.ingredientFactory.createCheese();
		pepperoni = this.ingredientFactory.createPepperoni();
		clam = this.ingredientFactory.createClam();
	}
}
