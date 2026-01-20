public class RoadTile extends Tile {
    @Override
    public char getCharacter() {
        return 'R';
    }

    @Override
    public String getType() {
        return "road";
    }

    @Override
    public void action() {
        System.out.println("Walking on road - movement boosted!");
    }
}
