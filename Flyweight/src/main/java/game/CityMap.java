package main.java.game;

public class CityMap extends Map {
    public CityMap(int width, int height) { super(width, height); }

    @Override
    public Tile createTile() {
        switch ((int)(Math.random() * 3)) {
            case 0:  return new RoadTile();
            case 1:  return new ForestTile();
            default: return new BuildingTile();
        }
    }
}