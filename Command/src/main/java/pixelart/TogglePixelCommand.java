package pixelart;

public class TogglePixelCommand implements Command {
    private Grid grid;
    private int x;
    private int y;
    private int previousValue;

    public TogglePixelCommand(Grid grid) {
        this.grid = grid;
        this.x = grid.getCursorX();
        this.y = grid.getCursorY();
    }

    @Override
    public void execute() {
        previousValue = grid.getPixel(x, y);
        int newValue = previousValue == 0 ? 1 : 0;
        grid.setPixel(x, y, newValue);
    }

    @Override
    public void undo() {
        grid.setPixel(x, y, previousValue);
    }
}