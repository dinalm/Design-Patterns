public class BuildingTile extends Tile {
    @Override
    public char getCharacter() {
        return 'B';
    }

    @Override
    public String getType() {
        return "building";
    }

    @Override
    public void action() {
        System.out.println("Entering building - safe zone!");
    }
}
