package main.java.game;

public abstract class Map {
    protected int      width;
    protected int      height;
    protected Tile[][] tiles;

    public Map(int width, int height) {
        this.width  = width;
        this.height = height;
        this.tiles  = new Tile[height][width];
        generateMap();
    }

    // Factory Method – each concrete main.java.game.Map decides which main.java.game.Tile types to create.
    public abstract Tile createTile();

    private void generateMap() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                tiles[i][j] = createTile();
    }

    public Tile[][] getTiles()  { return tiles;  }
    public int      getWidth()  { return width;  }
    public int      getHeight() { return height; }

    // Console display retained for debugging
    public void display() {
        System.out.println("\nmain.java.game.Map:");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(tiles[i][j].getCharacter() + " ");
            System.out.println();
        }
        System.out.println();
    }
}