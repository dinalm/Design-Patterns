package main.java.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TileGraphic {

    // Intrinsic state
    private final Color  fillColor;
    private final Color  borderColor;
    private final String symbol;
    private final String label;

    public TileGraphic(Color fillColor, Color borderColor, String symbol, String label) {
        this.fillColor   = fillColor;
        this.borderColor = borderColor;
        this.symbol      = symbol;
        this.label       = label;
    }

    // Flyweight operation
    public void render(GraphicsContext gc, double x, double y, double tileSize) {
        // Background
        gc.setFill(fillColor);
        gc.fillRoundRect(x + 1, y + 1, tileSize - 2, tileSize - 2, 8, 8);

        // Border
        gc.setStroke(borderColor);
        gc.setLineWidth(1.5);
        gc.strokeRoundRect(x + 1, y + 1, tileSize - 2, tileSize - 2, 8, 8);

        // Symbol centred in the upper portion of the cell
        gc.setFont(Font.font("Segoe UI Emoji", FontWeight.NORMAL, tileSize * 0.40));
        gc.setFill(Color.WHITE);
        gc.fillText(symbol, x + tileSize * 0.18, y + tileSize * 0.62);

        // Label text at the bottom of the cell
        gc.setFont(Font.font("Arial", FontWeight.BOLD, tileSize * 0.16));
        gc.setFill(Color.WHITE.deriveColor(0, 1, 1, 0.85));
        gc.fillText(label, x + 4, y + tileSize - 5);
    }

    // Getters
    public Color  getFillColor()   { return fillColor;   }
    public Color  getBorderColor() { return borderColor; }
    public String getSymbol()      { return symbol;      }
    public String getLabel()       { return label;       }
}