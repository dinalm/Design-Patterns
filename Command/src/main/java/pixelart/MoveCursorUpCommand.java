package pixelart;

public class MoveCursorUpCommand implements Command {
    private Grid grid;
    private int previousY;

    public MoveCursorUpCommand(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        previousY = grid.getCursorY();
        grid.setCursorY(grid.getCursorY() - 1);
    }

    @Override
    public void undo() {
        grid.setCursorY(previousY);
    }
}