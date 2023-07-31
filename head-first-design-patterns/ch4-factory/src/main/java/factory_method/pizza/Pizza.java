package factory_method.pizza;

import abstract_factory.ingredient.Cheese;
import abstract_factory.ingredient.Clams;
import abstract_factory.ingredient.Dough;
import abstract_factory.ingredient.Pepperoni;
import abstract_factory.ingredient.Sauce;
import abstract_factory.ingredient.Veggies;

public abstract class Pizza {
	protected String name;

	protected Dough dough;
	protected Sauce sauce;
	protected Veggies veggies[];
	protected Cheese cheese;
	protected Pepperoni pepperoni;
	protected Clams clam;

	public Pizza() {
		this("베이직");
	}

	protected Pizza(String name) {
		this.name = name;
	}

	public abstract void prepare();

	public void bake() {
		System.out.println(this.name + " 피자를 굽는다.");
	}

	public void cut() {
		System.out.println(this.name + " 피자를 자른다.");
	}

	public void box() {
		System.out.println(this.name + " 피자를 포장한다.");
	}
}
