package main.java.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MapRenderer {

    private final TileGraphicFactory factory;
    private final double             tileSize;

    public MapRenderer(TileGraphicFactory factory, double tileSize) {
        this.factory  = factory;
        this.tileSize = tileSize;
    }

    public void render(Canvas canvas, Map map) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear background
        gc.setFill(Color.web("#1a1a2e"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Tile[][] tiles = map.getTiles();

        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {

                // Extrinsic state: position is computed here and NOT stored in the flyweight
                double x = col * tileSize;
                double y = row * tileSize;

                // Retrieve the shared flyweight for this tile type
                TileGraphic graphic = factory.getGraphic(tiles[row][col].getType());

                // Delegate drawing; pass position as extrinsic parameters
                graphic.render(gc, x, y, tileSize);
            }
        }
    }

    public void renderLegend(Canvas canvas, Map map, String mapTitle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double legendX     = map.getWidth() * tileSize + 12;
        double legendY     = 10;
        double swatchSize  = 28;
        double rowHeight   = 38;

        // Title
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gc.setFill(Color.WHITE);
        gc.fillText(mapTitle + " Legend", legendX, legendY + 14);
        legendY += 28;

        // Collect unique tile types present in this map
        java.util.LinkedHashSet<String> seen = new java.util.LinkedHashSet<>();
        for (Tile[] row : map.getTiles())
            for (Tile t : row)
                seen.add(t.getType());

        for (String type : seen) {
            TileGraphic g = factory.getGraphic(type);

            // Colour swatch
            gc.setFill(g.getFillColor());
            gc.fillRoundRect(legendX, legendY, swatchSize, swatchSize, 6, 6);
            gc.setStroke(g.getBorderColor());
            gc.setLineWidth(1);
            gc.strokeRoundRect(legendX, legendY, swatchSize, swatchSize, 6, 6);

            // Symbol inside swatch
            gc.setFont(Font.font("Segoe UI Emoji", swatchSize * 0.55));
            gc.setFill(Color.WHITE);
            gc.fillText(g.getSymbol(), legendX + 3, legendY + swatchSize * 0.78);

            // Label
            gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            gc.setFill(Color.LIGHTGRAY);
            String display = type.substring(0, 1).toUpperCase() + type.substring(1);
            gc.fillText(display, legendX + swatchSize + 8, legendY + swatchSize * 0.65);

            legendY += rowHeight;
        }

        // Memory stat
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        gc.setFill(Color.web("#90CAF9"));
        gc.fillText("Flyweight pool: " + factory.poolSize() + " graphic(s)", legendX, legendY + 10);
    }
}