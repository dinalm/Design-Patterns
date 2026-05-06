package pixelart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PixelArtEditor extends Application {
    private Grid grid;
    private CommandInvoker invoker;
    private Canvas canvas;
    private static final int PIXEL_SIZE = 40;
    private static final int GRID_SIZE = 8;

    @Override
    public void start(Stage primaryStage) {
        grid = new Grid();
        invoker = new CommandInvoker();

        primaryStage.setTitle("Pixel Art Editor");
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);

        BorderPane root = new BorderPane();

        // Canvas for drawing
        canvas = new Canvas(PIXEL_SIZE * GRID_SIZE, PIXEL_SIZE * GRID_SIZE);
        root.setCenter(canvas);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button generateCodeButton = new Button("Generate Code");
        Button clearButton = new Button("Clear");
        Button undoButton = new Button("Undo");

        generateCodeButton.setOnAction(e -> {
            invoker.executeCommand(new GenerateCodeCommand(grid));
            draw();
            canvas.requestFocus();  // ✅ FIX 1
        });

        clearButton.setOnAction(e -> {
            grid.clear();
            draw();
            canvas.requestFocus();  // ✅ FIX 1
        });

        undoButton.setOnAction(e -> {
            invoker.undo();
            draw();
            canvas.requestFocus();  // ✅ FIX 1
        });

        buttonBox.getChildren().addAll(generateCodeButton, clearButton, undoButton);
        buttonBox.setStyle("-fx-padding: 10;");
        root.setBottom(buttonBox);

        Scene scene = new Scene(root);

        // Keyboard controls
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            boolean handled = false;  // ✅ FIX 2

            if (code == KeyCode.UP) {
                invoker.executeCommand(new MoveCursorUpCommand(grid));
                handled = true;  // ✅ FIX 2
            } else if (code == KeyCode.DOWN) {
                invoker.executeCommand(new MoveCursorDownCommand(grid));
                handled = true;  // ✅ FIX 2
            } else if (code == KeyCode.LEFT) {
                invoker.executeCommand(new MoveCursorLeftCommand(grid));
                handled = true;  // ✅ FIX 2
            } else if (code == KeyCode.RIGHT) {
                invoker.executeCommand(new MoveCursorRightCommand(grid));
                handled = true;  // ✅ FIX 2
            } else if (code == KeyCode.SPACE) {
                invoker.executeCommand(new TogglePixelCommand(grid));
                handled = true;  // ✅ FIX 2
            }

            if (handled) {
                event.consume();  // ✅ FIX 3
                draw();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        canvas.requestFocus();
        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw grid
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                int pixelValue = grid.getPixel(x, y);
                Color color = pixelValue == 1 ? Color.BLACK : Color.WHITE;
                gc.setFill(color);
                gc.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);

                // Draw border
                gc.setStroke(Color.GRAY);
                gc.strokeRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
            }
        }

        // Draw cursor
        int cursorX = grid.getCursorX();
        int cursorY = grid.getCursorY();
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        gc.strokeRect(cursorX * PIXEL_SIZE, cursorY * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}