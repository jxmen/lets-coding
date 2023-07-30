package factory_method;

import factory_method.factory.NYStylePizzaFactory;
import factory_method.store.ChicagoSytlePizzaStore;
import factory_method.store.NyStylePizzaStore;
import factory_method.store.PizzaStore;

public class Main {
	public static void main(String[] args) {
		PizzaStore nyPizzaStore = new NyStylePizzaStore();
		nyPizzaStore.orderPizza("치즈");

		PizzaStore chicagoPizzaStore = new ChicagoSytlePizzaStore();
		chicagoPizzaStore.orderPizza("치즈");
	}
}
