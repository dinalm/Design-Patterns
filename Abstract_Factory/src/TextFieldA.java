public class TextFieldA extends TextField {

    public TextFieldA(String text) {
        super(text);
    }

    @Override
    public void display() {
        // Content with brackets
        System.out.println("[ " + text + " ]");

        // Underline
        System.out.print("  ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}
