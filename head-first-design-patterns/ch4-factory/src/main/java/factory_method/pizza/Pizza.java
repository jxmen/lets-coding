package factory_method.pizza;

public abstract class Pizza {
	protected String name;

	public Pizza() {
		this("베이직");
	}

	protected Pizza(String name) {
		this.name = name;
	}

	public void prepare() {
		System.out.println(this.name + " 피자를 준비한다.");
	}

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
