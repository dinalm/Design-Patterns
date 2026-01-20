public class SwampTile extends Tile {
    @Override
    public char getCharacter() {
        return 'S';
    }

    @Override
    public String getType() {
        return "swamp";
    }

    @Override
    public void action() {
        System.out.println("Moving through swamp - movement slowed!");
    }
}
