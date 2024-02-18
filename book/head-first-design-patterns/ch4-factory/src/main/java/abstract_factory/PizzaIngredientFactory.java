package abstract_factory;

import abstract_factory.ingredient.Cheese;
import abstract_factory.ingredient.Clams;
import abstract_factory.ingredient.Dough;
import abstract_factory.ingredient.Pepperoni;
import abstract_factory.ingredient.Sauce;
import abstract_factory.ingredient.Veggies;

public interface PizzaIngredientFactory {

	public Dough createDough();

	public Sauce createSauce();

	public Cheese createCheese();

	public Veggies[] createVeggies();

	public Pepperoni createPepperoni();

	public Clams createClam();
}
