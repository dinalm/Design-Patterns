package main.java.game;

public class WildernessMap extends Map {
    public WildernessMap(int width, int height) { super(width, height); }

    @Override
    public Tile createTile() {
        switch ((int)(Math.random() * 3)) {
            case 0:  return new SwampTile();
            case 1:  return new WaterTile();
            default: return new ForestTile();
        }
    }
}