public class CityMap extends Map {
    public CityMap(int width, int height) {
        super(width, height);
    }

    @Override
    public Tile createTile() {
        // City maps contain: road, forest, and building
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                return new RoadTile();
            case 1:
                return new ForestTile();
            case 2:
                return new BuildingTile();
            default:
                return new RoadTile();
        }
    }
}
