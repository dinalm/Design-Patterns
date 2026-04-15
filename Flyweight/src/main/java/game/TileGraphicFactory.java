package main.java.game;

import javafx.scene.paint.Color;
import java.util.HashMap;

public class TileGraphicFactory {

    // The Flyweight pool: type key → shared main.java.game.TileGraphic instance.
    private final HashMap<String, TileGraphic> pool = new HashMap<>();

    // Creates and caches the object on the first call for each type.
    public TileGraphic getGraphic(String tileType) {
        if (!pool.containsKey(tileType)) {
            pool.put(tileType, createGraphic(tileType));
            System.out.println("[main.java.game.TileGraphicFactory] Created new main.java.game.TileGraphic for type: " + tileType);
        }
        return pool.get(tileType);
    }

    // Returns how many unique main.java.game.TileGraphic objects exist in the pool.
    public int poolSize() { return pool.size(); }

    // Private factory for each tile type

    private TileGraphic createGraphic(String type) {
        switch (type) {
            case "road":
                return new TileGraphic(
                        Color.web("#607D8B"),   // blue-grey asphalt
                        Color.web("#37474F"),
                        "\uD83D\uDEE3",        // 🛣
                        "ROAD"
                );
            case "forest":
                return new TileGraphic(
                        Color.web("#388E3C"),   // dark green
                        Color.web("#1B5E20"),
                        "\uD83C\uDF32",        // 🌲
                        "FOREST"
                );
            case "building":
                return new TileGraphic(
                        Color.web("#5D4037"),   // brown brick
                        Color.web("#3E2723"),
                        "\uD83C\uDFDB",        // 🏛
                        "BUILDING"
                );
            case "swamp":
                return new TileGraphic(
                        Color.web("#827717"),   // muddy olive
                        Color.web("#558B2F"),
                        "\uD83C\uDF3F",        // 🌿
                        "SWAMP"
                );
            case "water":
                return new TileGraphic(
                        Color.web("#1565C0"),   // deep blue
                        Color.web("#0D47A1"),
                        "\uD83C\uDF0A",        // 🌊
                        "WATER"
                );
            default:
                // Fallback for any future tile types
                return new TileGraphic(
                        Color.DARKGRAY,
                        Color.BLACK,
                        "?",
                        type.toUpperCase()
                );
        }
    }
}