public class Game {
    // Factory method to create different types of maps
    public Map createMap(String mapType, int width, int height) {
        if (mapType.equalsIgnoreCase("city")) {
            return new CityMap(width, height);
        } else if (mapType.equalsIgnoreCase("wilderness")) {
            return new WildernessMap(width, height);
        }
        return null;
    }

    public static void main(String[] args) {
        Game game = new Game();

        // Create and display a City Map
        System.out.println("=== CITY MAP ===");
        Map cityMap = game.createMap("city", 10, 5);
        cityMap.display();

        // Create and display a Wilderness Map
        System.out.println("=== WILDERNESS MAP ===");
        Map wildernessMap = game.createMap("wilderness", 10, 5);
        wildernessMap.display();
    }
}
