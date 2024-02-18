interface Command {
    execute(): void;
}

class SimpleRemoteControl {
    private slot: Command;

    constructor() {
        this.slot = new class implements Command {
            execute(): void {
                console.log("default slot");
            }
        }
    }

    public setCommand(slot: Command): void {
       this.slot = slot;
    }

    public buttonWasPressed(): void {
        this.slot.execute();
    }
}

// receiver
class Light {

    // action
    on() {
        console.log("조명이 켜졌습니다.");
    }
}

class LightOnCommand implements Command {
    // reciver
    private light: Light;

    constructor(light: Light) {
        this.light = light;
    }

    execute(): void {
        this.light.on(); // receiver's action
    }

}

function main() {
    const simpleRemoteControl = new SimpleRemoteControl();

    // receiver
    const light: Light = new Light();

    // invoker
    const lightOnCommand: LightOnCommand = new LightOnCommand(light);

    simpleRemoteControl.buttonWasPressed();
    simpleRemoteControl.setCommand(lightOnCommand);
    simpleRemoteControl.buttonWasPressed();
}

main();
