public class WildernessMap extends Map {
    public WildernessMap(int width, int height) {
        super(width, height);
    }

    @Override
    public Tile createTile() {
        // Wilderness maps contain: swamp, water, and forest
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                return new SwampTile();
            case 1:
                return new WaterTile();
            case 2:
                return new ForestTile();
            default:
                return new SwampTile();
        }
    }
}
