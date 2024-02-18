interface Duck {
   quack(): void;
   fly(): void;
}

class MallardDuck implements Duck {
   quack(): void {
      console.log("Quack");
   }
   fly(): void {
      console.log("I'm flying");
   }
}

interface Turkey {
    gobble(): void;
    fly(): void;
}

class TurkeyAdapter implements Duck {
    turkey: Turkey;

    constructor(turkey: Turkey) {
        this.turkey = turkey;
    }

    fly(): void {
        // turkey fly 5 times
        for (let i = 0; i < 5; i++) {
            this.turkey.fly();
        }
    }

    quack(): void {
       this.turkey.gobble();
    }
}

class WildTurkey implements Turkey {
    fly(): void {
        console.log("I'm flying a short distance");
    }

    gobble(): void {
        console.log("Gobble gobble");
    }
}

class DuckAdapter implements Turkey {
    private duck: Duck;
    private randInt: number;

    constructor(duck: Duck) {
        this.duck = duck;
        this.randInt = Math.floor(Math.random() * 5);
    }

    fly(): void {
        if (this.randInt % 5 === 0) {
            this.duck.fly();
        }
    }

    gobble(): void {
        this.duck.quack();
    }
}

function main() {
    const duck: Duck = new MallardDuck();
    const turkey: Turkey = new WildTurkey();

    const turkeyAdapter: Duck = new TurkeyAdapter(turkey);
    const duckAdapter: Turkey = new DuckAdapter(duck);

    console.log("\nThe Turkey says...");
    testTurkey(turkey);

    console.log("\nThe DuckAdapter says...");
    testTurkey(duckAdapter);

    console.log("\nThe Duck says...");
    testDuck(duck);

    console.log("\nThe TurkeyAdapter says...");
    testDuck(turkeyAdapter);

    function testDuck(duck: Duck) {
        duck.quack();
        duck.fly();
    }

    function testTurkey(turkey: Turkey) {
        turkey.gobble();
        turkey.fly();
    }

}

main();
