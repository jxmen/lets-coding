export interface QuackBehavior {
    quack(): void;
}

export interface FlyBehavior {
    fly(): void;
}

export default abstract class Duck {
    public quackBehavior: QuackBehavior;
    public flyBehavior: FlyBehavior;

    constructor(quackBehavior: QuackBehavior, flyBehavior: FlyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    public performQuack(): void {
        this.quackBehavior.quack();
    }

    public performFly(): void {
        this.flyBehavior.fly();
    }
}
