import Duck, {FlyBehavior, QuackBehavior} from "./Duck";

class FlyWithWings implements FlyBehavior{
    fly(): void {
        console.log("날고 있어요");
    }
}

class Quack implements QuackBehavior {
    quack(): void {
        console.log("꽥");
    }
}

export default class MallardDuck extends Duck {

    constructor() {
        super();
        super.quackBehavior = new Quack();
        super.flyBehavior = new FlyWithWings();
    }
};
