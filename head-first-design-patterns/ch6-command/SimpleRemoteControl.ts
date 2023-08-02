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
class Light {
    on() {
        console.log("조명이 켜졌습니다.");
    }
}

class LightOnCommand implements Command {
    private light: Light;

    constructor(light: Light) {
        this.light = light;
    }

    execute(): void {
        this.light.on();
    }

}

function main() {
    const simpleRemoteControl = new SimpleRemoteControl();

    const light: Light = new Light();
    const lightOn: LightOnCommand = new LightOnCommand(light);

    simpleRemoteControl.buttonWasPressed();
    simpleRemoteControl.setCommand(lightOn);
    simpleRemoteControl.buttonWasPressed();
}

main();
