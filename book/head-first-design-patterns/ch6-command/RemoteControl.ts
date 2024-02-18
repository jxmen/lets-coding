interface Command {
    execute(): void;

    undo(): void;
}

class NoCommand implements Command {
    execute(): void {
    }

    undo(): void {
    }
}

class RemoteControl {
    private onCommands: Command[];
    private offCommands: Command[];
    private undoCommand: Command;

    constructor() {
        this.onCommands = new Array(7);
        this.offCommands = new Array(7);

        const noCommand: Command = new NoCommand();
        this.onCommands.forEach(command => command = noCommand);
        this.offCommands.forEach(command => command = noCommand);
        this.undoCommand = noCommand;
    }

    public setCommand(slot: number, onCommand: Command, offCommand: Command) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public onButtonWasPushed(slot: number) {
        this.onCommands[slot].execute();
        this.undoCommand = this.onCommands[slot];
    }

    public offButtonWasPushed(slot: number) {
        this.offCommands[slot].execute();
        this.undoCommand = this.offCommands[slot];
    }

    public undoButtonWasPushed() {
        this.undoCommand.undo();
    }
}

class OnCommand implements Command {
    private light: Light;

    constructor(light: Light) {
        this.light = light;
    }

    execute(): void {
        this.light.on();
    }

    undo(): void {
        this.light.off();
    }
}

class Light {
    off() {
        console.log("조명이 꺼졌습니다.");
    }

    on() {
        console.log("조명이 켜졌습니다.");
    }
}

class OffCommand implements Command {
    private light: Light;

    constructor(light: Light) {
        this.light = light;
    }

    execute(): void {
        this.light.off();
    }

    undo(): void {
        this.light.on();
    }
}

function main() {
    const remoteControl = new RemoteControl();

    const light: Light = new Light();
    const onCommand: Command = new OnCommand(light);
    const offCommand: Command = new OffCommand(light);

    remoteControl.setCommand(0, onCommand, offCommand);
    remoteControl.onButtonWasPushed(0);
    remoteControl.undoButtonWasPushed();
    remoteControl.offButtonWasPushed(0);
}

main();
