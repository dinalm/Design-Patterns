import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;

public class Gui extends Application {

    private Controller controller;
    private ColorBox colorBox1;
    private ColorBox colorBox2;
    private ColorBox colorBox3;
    private CheckBox checkBox;

    private Stage historyStage;
    private ListView<IMemento> historyListView;

    public void start(Stage stage) {
        controller = new Controller(this);

        Insets insets = new Insets(10, 10, 10, 10);

        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);

        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);

        HBox hBox = new HBox(colorBox1.getRectangle(), colorBox2.getRectangle(), colorBox3.getRectangle());
        hBox.setSpacing(10);
        hBox.setMargin(colorBox1.getRectangle(), insets);
        hBox.setMargin(colorBox2.getRectangle(), insets);
        hBox.setMargin(colorBox3.getRectangle(), insets);

        Label label = new Label("Ctrl-Z to undo  |  Ctrl-Y to redo");
        label.setPadding(insets);

        VBox vBox = new VBox(hBox, checkBox, label);

        checkBox.setOnAction(event -> controller.setIsSelected(checkBox.isSelected()));

        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                System.out.println("Undo key pressed");
                controller.undo();
            } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                System.out.println("Redo key pressed");
                controller.redo();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Memento Pattern Example");
        stage.show();

        openHistoryWindow();
    }

    private void openHistoryWindow() {
        historyListView = new ListView<>();
        historyListView.setPrefSize(400, 300);

        historyListView.setOnMouseClicked(event -> {
            IMemento selected = historyListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                controller.restoreFromHistory(selected);
            }
        });

        VBox vBox = new VBox(new Label("Click a state to restore it:"), historyListView);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10));

        historyStage = new Stage();
        historyStage.setTitle("History");
        historyStage.setScene(new Scene(vBox));
        historyStage.show();
    }

    public void refreshHistoryWindow() {
        if (historyListView != null) {
            ObservableList<IMemento> items =
                    FXCollections.observableArrayList(controller.getHistory());
            historyListView.setItems(items);
        }
    }

    public void updateGui() {
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
    }
}
