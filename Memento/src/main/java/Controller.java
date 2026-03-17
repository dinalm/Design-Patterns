import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Model model;
    private Gui gui;
    private List<IMemento> history;
    private List<IMemento> redoList;

    public Controller(Gui gui) {
        this.model    = new Model();
        this.gui      = gui;
        this.history  = new ArrayList<>();
        this.redoList = new ArrayList<>();
    }

    public void setOption(int optionNumber, int choice) {
        saveToHistory();
        model.setOption(optionNumber, choice);
        redoList.clear();
        gui.refreshHistoryWindow();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        model.setIsSelected(isSelected);
        redoList.clear();
        gui.refreshHistoryWindow();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    // Ctrl-Z: save current to redo, restore last from undo
    public void undo() {
        if (!history.isEmpty()) {
            System.out.println("Undo");
            redoList.add(model.createMemento());
            IMemento previousState = history.remove(history.size() - 1);
            model.restoreState(previousState);
            gui.updateGui();
            gui.refreshHistoryWindow();
        }
    }

    // Ctrl-Y: save current to undo, restore last from redo
    public void redo() {
        if (!redoList.isEmpty()) {
            System.out.println("Redo");
            saveToHistory();
            IMemento nextState = redoList.remove(redoList.size() - 1);
            model.restoreState(nextState);
            gui.updateGui();
            gui.refreshHistoryWindow();
        }
    }

    // Called when user clicks a state in the history window
    public void restoreFromHistory(IMemento memento) {
        saveToHistory();
        redoList.clear();
        model.restoreState(memento);
        gui.updateGui();
        gui.refreshHistoryWindow();
    }

    public List<IMemento> getHistory() {
        return history;
    }

    private void saveToHistory() {
        history.add(model.createMemento());
    }
}