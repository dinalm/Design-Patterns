public class UIApplication {

    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern - ASCII UI Demo ===\n");

        System.out.println(">>> Creating UI with Style A (Minimalist) <<<\n");
        UIFactory factoryA = new AFactory();
        demonstrateFactory(factoryA);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println(">>> Creating UI with Style B (Bold) <<<\n");
        UIFactory factoryB = new BFactory();
        demonstrateFactory(factoryB);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println(">>> Demonstrating Dynamic Text Change <<<\n");
        demonstrateTextChange(factoryA);
    }

    private static void demonstrateFactory(UIFactory factory) {
        // Create Button
        Button button = factory.createButton("Click Me");
        System.out.println("Button:");
        button.display();
        System.out.println();

        // Create TextField
        TextField textField = factory.createTextField("Enter your name");
        System.out.println("TextField:");
        textField.display();
        System.out.println();

        // Create Checkbox
        Checkbox checkbox = factory.createCheckbox("I agree to terms");
        System.out.println("Checkbox:");
        checkbox.display();
    }

    private static void demonstrateTextChange(UIFactory factory) {
        Button button = factory.createButton("Submit");
        System.out.println("Original Button:");
        button.display();
        System.out.println();

        // Change the text
        button.setText("Processing...");
        System.out.println("After setText('Processing...'):");
        button.display();
        System.out.println();

        // Change again
        button.setText("Done!");
        System.out.println("After setText('Done!'):");
        button.display();
        System.out.println();

        // Demonstrate with TextField
        TextField textField = factory.createTextField("Initial Value");
        System.out.println("Original TextField:");
        textField.display();
        System.out.println();

        textField.setText("Updated Value");
        System.out.println("After setText('Updated Value'):");
        textField.display();
    }
}