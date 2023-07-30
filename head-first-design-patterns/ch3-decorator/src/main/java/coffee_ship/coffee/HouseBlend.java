package coffee_ship.coffee;

import coffee_ship.beverage.Beverage;

public class HouseBlend extends Beverage {

	public HouseBlend() {
		this.description = "하우스 블렌드 커피";
	}

	@Override
	public double cost() {
		return .89;
	}
}
