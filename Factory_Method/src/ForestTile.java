public class ForestTile extends Tile {
    @Override
    public char getCharacter() {
        return 'F';
    }

    @Override
    public String getType() {
        return "forest";
    }

    @Override
    public void action() {
        System.out.println("Moving through forest - watch for wildlife!");
    }
}
