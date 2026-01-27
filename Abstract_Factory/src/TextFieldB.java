public class TextFieldB extends TextField {

    public TextFieldB(String text) {
        super(text);
    }

    @Override
    public void display() {
        // Content with angle brackets
        System.out.println("<< " + text + " >>");

        // Double underline
        System.out.print("   ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
