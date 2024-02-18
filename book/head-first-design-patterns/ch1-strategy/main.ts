import MallardDuck from "./MallarDuck";
import {FlyBehavior} from "./Duck";

class FlyNoWay implements FlyBehavior {
    fly(): void {
        console.log("날 수 없어요");
    }
}

function main() {
    const mallardDuck = new MallardDuck();
    mallardDuck.performQuack(); // 꽥
    mallardDuck.performFly(); // 날고 있어요

    mallardDuck.setFlyBehavior(new FlyNoWay()); // 나는 전략 변경

    mallardDuck.performFly(); // 날 수 없어요
}

main();
