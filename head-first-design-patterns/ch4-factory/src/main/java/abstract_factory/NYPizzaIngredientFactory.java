package abstract_factory;

import abstract_factory.ingredient.Cheese;
import abstract_factory.ingredient.Clams;
import abstract_factory.ingredient.Dough;
import abstract_factory.ingredient.FreshClams;
import abstract_factory.ingredient.Garlic;
import abstract_factory.ingredient.MarinaraSauce;
import abstract_factory.ingredient.Mushroom;
import abstract_factory.ingredient.Onion;
import abstract_factory.ingredient.Pepperoni;
import abstract_factory.ingredient.RedPepper;
import abstract_factory.ingredient.ReggianoCheese;
import abstract_factory.ingredient.Sauce;
import abstract_factory.ingredient.SlicedPepperoni;
import abstract_factory.ingredient.ThinCrustDough;
import abstract_factory.ingredient.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinaraSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		return new Veggies[]{
			new Garlic(), new Onion(), new Mushroom(), new RedPepper()
		};
	}

	@Override
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Override
	public Clams createClam() {
		return new FreshClams();
	}

}
