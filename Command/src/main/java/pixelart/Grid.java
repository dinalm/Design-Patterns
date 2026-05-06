package pixelart;

public class Grid {
    private static final int SIZE = 8;
    private int[][] pixels;
    private int cursorX;
    private int cursorY;

    public Grid() {
        this.pixels = new int[SIZE][SIZE];
        this.cursorX = 0;
        this.cursorY = 0;
    }

    public int getPixel(int x, int y) {
        return pixels[y][x];
    }

    public void setPixel(int x, int y, int value) {
        pixels[y][x] = value;
    }

    public int getCursorX() {
        return cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public void setCursorX(int x) {
        if (x >= 0 && x < SIZE) {
            this.cursorX = x;
        }
    }

    public void setCursorY(int y) {
        if (y >= 0 && y < SIZE) {
            this.cursorY = y;
        }
    }

    public int getSize() {
        return SIZE;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void clear() {
        this.pixels = new int[SIZE][SIZE];
    }
}