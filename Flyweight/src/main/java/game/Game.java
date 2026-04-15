package main.java.game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Game extends Application {

    // Map dimensions
    private static final int MAP_WIDTH  = 20;
    private static final int MAP_HEIGHT = 15;
    private static final double TILE_SIZE = 48;

    // Canvas dimensions
    private static final double CANVAS_W = MAP_WIDTH  * TILE_SIZE;
    private static final double CANVAS_H = MAP_HEIGHT * TILE_SIZE;
    private static final double LEGEND_W = 150;

    // Shared Flyweight factory – both maps reuse the same pool
    private final TileGraphicFactory factory  = new TileGraphicFactory();
    private final MapRenderer        renderer = new MapRenderer(factory, TILE_SIZE);

    // Current maps (regenerated on button click)
    private Map cityMap;
    private Map wildernessMap;

    // Canvases updated on each (re)generate
    private Canvas cityCanvas;
    private Canvas wildernessCanvas;

    @Override
    public void start(Stage stage) {
        // Phase 1: generate maps (Factory Method)
        cityMap       = new CityMap(MAP_WIDTH, MAP_HEIGHT);
        wildernessMap = new WildernessMap(MAP_WIDTH, MAP_HEIGHT);

        cityMap.display();
        wildernessMap.display();

        // Phase 2: create canvases
        cityCanvas       = new Canvas(CANVAS_W + LEGEND_W, CANVAS_H);
        wildernessCanvas = new Canvas(CANVAS_W + LEGEND_W, CANVAS_H);

        // Initial render
        renderer.render(cityCanvas,       cityMap);
        renderer.renderLegend(cityCanvas,       cityMap,       "City");
        renderer.render(wildernessCanvas, wildernessMap);
        renderer.renderLegend(wildernessCanvas, wildernessMap, "Wilderness");

        // UI layout
        Label title = new Label("RPG Map Generator – Flyweight Rendering");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label(
                "Factory Method generates tiles  ·  Flyweight shares TileGraphic objects  ·  "
                        + "Pool size shown in legend"
        );
        subtitle.setFont(Font.font("Arial", 12));
        subtitle.setTextFill(Color.web("#90CAF9"));

        // Tab labels
        Label cityLabel  = makeTabLabel("🏙  City Map");
        Label wildLabel  = makeTabLabel("🌿  Wilderness Map");

        // Tab canvases wrapped in scroll panes
        javafx.scene.control.Tab cityTab  = new javafx.scene.control.Tab("🏙  City Map",       cityCanvas);
        javafx.scene.control.Tab wildTab  = new javafx.scene.control.Tab("🌿  Wilderness Map", wildernessCanvas);
        cityTab.setClosable(false);
        wildTab.setClosable(false);

        javafx.scene.control.TabPane tabPane = new javafx.scene.control.TabPane(cityTab, wildTab);
        tabPane.setStyle("-fx-background-color: #1a1a2e;");

        // Regenerate button
        Button regenBtn = new Button("⟳  Regenerate Maps");
        regenBtn.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        regenBtn.setStyle(
                "-fx-background-color: #1565C0; -fx-text-fill: white; " +
                        "-fx-padding: 8 20; -fx-background-radius: 6;"
        );
        regenBtn.setOnAction(e -> regenerate());

        // Memory info label
        Label memLabel = new Label();
        memLabel.setFont(Font.font("Arial", 11));
        memLabel.setTextFill(Color.web("#80CBC4"));
        updateMemLabel(memLabel);
        regenBtn.setOnAction(e -> { regenerate(); updateMemLabel(memLabel); });

        HBox controls = new HBox(16, regenBtn, memLabel);
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setPadding(new Insets(8, 0, 0, 0));

        VBox root = new VBox(10, title, subtitle, tabPane, controls);
        root.setPadding(new Insets(16));
        root.setStyle("-fx-background-color: #1a1a2e;");

        Scene scene = new Scene(root, CANVAS_W + LEGEND_W + 40, CANVAS_H + 120);
        scene.setFill(Color.web("#1a1a2e"));

        stage.setTitle("RPG Flyweight Map Renderer");
        stage.setScene(scene);
        stage.show();
    }

    /** Regenerate both maps and re-render onto the existing canvases. */
    private void regenerate() {
        cityMap       = new CityMap(MAP_WIDTH, MAP_HEIGHT);
        wildernessMap = new WildernessMap(MAP_WIDTH, MAP_HEIGHT);

        renderer.render(cityCanvas,       cityMap);
        renderer.renderLegend(cityCanvas,       cityMap,       "City");
        renderer.render(wildernessCanvas, wildernessMap);
        renderer.renderLegend(wildernessCanvas, wildernessMap, "Wilderness");

        System.out.println("[Game] Maps regenerated. Flyweight pool size: " + factory.poolSize());
    }

    private void updateMemLabel(Label lbl) {
        int totalCells   = MAP_WIDTH * MAP_HEIGHT * 2;
        int poolSize     = factory.poolSize();
        lbl.setText(
                "Total tile cells: " + totalCells +
                        "   |   TileGraphic objects in pool: " + poolSize +
                        "   |   Memory saved: ~" + (totalCells - poolSize) + " graphic objects"
        );
    }

    private Label makeTabLabel(String text) {
        Label l = new Label(text);
        l.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        l.setTextFill(Color.WHITE);
        return l;
    }

    // Factory Method helper
    public Map createMap(String type, int width, int height) {
        if (type.equalsIgnoreCase("city"))       return new CityMap(width, height);
        if (type.equalsIgnoreCase("wilderness")) return new WildernessMap(width, height);
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}