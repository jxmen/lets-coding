package coffee_ship;

import coffee_ship.beverage.Beverage;
import coffee_ship.coffee.HouseBlend;
import coffee_ship.condiment.impl.Milk;
import coffee_ship.condiment.impl.Mocha;

public class Main {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend();

		System.out.println("coffee_ship.beverage.cost() = " + beverage.cost());
		System.out.println("coffee_ship.beverage.getDescription() = " + beverage.getDescription());

		beverage =  new Mocha(beverage);

		System.out.println("coffee_ship.beverage.cost() = " + beverage.cost());
		System.out.println("coffee_ship.beverage.getDescription() = " + beverage.getDescription());

		beverage = new Milk(beverage);

		System.out.println("coffee_ship.beverage.cost() = " + beverage.cost());
		System.out.println("coffee_ship.beverage.getDescription() = " + beverage.getDescription());
	}
}
