public class ButtonA extends Button {

    public ButtonA(String text) {
        super(text);
    }

    @Override
    public void display() {
        int width = text.length() + 4;

        // Top border
        System.out.print("( ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print("-");
        }
        System.out.println(" )");

        // Content
        System.out.println("| " + text + " |");

        // Bottom border
        System.out.print("( ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print("-");
        }
        System.out.println(" )");
    }
}
