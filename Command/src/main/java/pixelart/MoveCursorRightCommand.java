package pixelart;

public class MoveCursorRightCommand implements Command {
    private Grid grid;
    private int previousX;

    public MoveCursorRightCommand(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        previousX = grid.getCursorX();
        grid.setCursorX(grid.getCursorX() + 1);
    }

    @Override
    public void undo() {
        grid.setCursorX(previousX);
    }
}