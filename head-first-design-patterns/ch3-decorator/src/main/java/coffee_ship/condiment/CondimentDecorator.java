package coffee_ship.condiment;

import coffee_ship.beverage.Beverage;

/**
 * 첨가물
 *
 */
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;

	public abstract String getDescription();
}
