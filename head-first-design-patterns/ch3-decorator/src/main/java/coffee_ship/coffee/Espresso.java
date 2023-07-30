package coffee_ship.coffee;

import coffee_ship.beverage.Beverage;

public class Espresso extends Beverage {

	public Espresso() {
		this.description = "에스프레스";
	}

	@Override
	public double cost() {
		return 1.99;
	}
}
