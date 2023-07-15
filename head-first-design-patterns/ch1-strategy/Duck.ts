export interface QuackBehavior {
    quack(): void;
}

export interface FlyBehavior {
    fly(): void;
}

export default abstract class Duck {
    quackBehavior: QuackBehavior;
    flyBehavior: FlyBehavior;


    public constructor() {
    }

    public performQuack(): void {
        this.quackBehavior.quack();
    }

    public performFly(): void {
        this.flyBehavior.fly();
    }
}
