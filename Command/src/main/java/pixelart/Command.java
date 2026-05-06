package pixelart;

public interface Command {
    void execute();
    void undo();
}