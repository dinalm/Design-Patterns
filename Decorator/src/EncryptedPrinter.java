import java.util.Base64;

public class EncryptedPrinter extends PrinterDecorator {

    public EncryptedPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        String encrypted = Base64.getEncoder().encodeToString(message.getBytes());
        printer.print(encrypted);
    }
}

