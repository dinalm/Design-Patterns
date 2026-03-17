import java.time.LocalDateTime;

public class Memento implements IMemento {

    private int[] options;
    private boolean isSelected;
    private LocalDateTime timestamp;

    public Memento(int[] options, boolean isSelected) {
        this.options    = options.clone();
        this.isSelected = isSelected;
        this.timestamp  = LocalDateTime.now();
        System.out.println("Memento created");
    }

    public int[] getOptions()   { return options; }
    public boolean isSelected() { return isSelected; }

    @Override
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return timestamp.toLocalTime().withNano(0)
                + "  |  boxes: " + options[0] + "," + options[1] + "," + options[2]
                + "  |  checkbox: " + isSelected;
    }
}