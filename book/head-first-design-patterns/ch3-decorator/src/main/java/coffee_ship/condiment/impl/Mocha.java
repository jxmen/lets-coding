package coffee_ship.condiment.impl;

import coffee_ship.beverage.Beverage;
import coffee_ship.condiment.CondimentDecorator;

public class Mocha extends CondimentDecorator {

	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return this.beverage.cost() + .20;
	}

	@Override
	public String getDescription() {
		return this.beverage.getDescription() + ", 모카";
	}
}
