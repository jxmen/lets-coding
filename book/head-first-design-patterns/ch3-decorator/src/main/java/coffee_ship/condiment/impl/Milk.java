package coffee_ship.condiment.impl;

import coffee_ship.beverage.Beverage;
import coffee_ship.condiment.CondimentDecorator;

public class Milk extends CondimentDecorator {

	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return this.beverage.cost() + .10;
	}

	@Override
	public String getDescription() {
		return this.beverage.getDescription() + ", 우유";
	}
}
