interface Command {
    execute(): void;
}

class NoCommand implements Command {
    execute(): void {
    }
}

class RemoteControl {
    private onCommands :Command[];
    private offCommands :Command[];

    constructor() {
        this.onCommands = new Array(7);
        this.offCommands = new Array(7);

        const noCommand: Command = new NoCommand();
        this.onCommands.forEach(command => command = noCommand);
        this.offCommands.forEach(command => command = noCommand);
    }

    public setCommand(slot: number, onCommand: Command, offCommand: Command) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public onCommandWasPushed(slot: number) {
        this.onCommands[slot].execute();
    }

    public offCommandWasPushed(slot: number) {
        this.offCommands[slot].execute();
    }
}
