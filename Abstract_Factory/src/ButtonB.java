public class ButtonB extends Button {

    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        int width = text.length() + 4;

        // Top border
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("=");
        }
        System.out.println("+");

        // Content with extra padding
        System.out.println("|| " + text + " ||");

        // Bottom border
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("=");
        }
        System.out.println("+");
    }
}
