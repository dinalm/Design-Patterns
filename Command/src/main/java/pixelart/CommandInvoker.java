package pixelart;

import java.util.Stack;

public class CommandInvoker {
    private Stack<Command> commandHistory;

    public CommandInvoker() {
        this.commandHistory = new Stack<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
        }
    }

    public void clearHistory() {
        commandHistory.clear();
    }
}