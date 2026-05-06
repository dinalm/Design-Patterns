package solutions.jokes;

import java.io.IOException;

public class ApiFacadeDemo {

    public static void main(String[] args) {
        ApiFacade facade = new ApiFacade();

        // ============================================================
        // Demo 1: Chuck Norris Jokes API
        // ============================================================
        System.out.println("=== Chuck Norris Jokes API Demo ===");
        demonstrateChuckNorrisJoke(facade);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // ============================================================
        // Demo 2: Exchange Rates API
        // ============================================================
        System.out.println("=== Exchange Rates API Demo ===");
        demonstrateExchangeRates(facade);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // ============================================================
        // Demo 3: Error Handling - Invalid URL
        // ============================================================
        System.out.println("=== Error Handling: Invalid URL ===");
        demonstrateInvalidUrl(facade);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // ============================================================
        // Demo 4: Error Handling - Attribute Not Found
        // ============================================================
        System.out.println("=== Error Handling: Attribute Not Found ===");
        demonstrateAttributeNotFound(facade);
    }

    /**
     * Demonstrates retrieving a random Chuck Norris joke using the facade.
     */
    private static void demonstrateChuckNorrisJoke(ApiFacade facade) {
        try {
            System.out.println("Fetching a random Chuck Norris joke...");
            String joke = facade.getAttributeValueFromJson(
                    "https://api.chucknorris.io/jokes/random",
                    "value"
            );
            System.out.println("✓ Success!");
            System.out.println("Joke: " + joke);
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Demonstrates retrieving exchange rates using the facade.
     */
    private static void demonstrateExchangeRates(ApiFacade facade) {
        try {
            System.out.println("Fetching EUR/USD exchange rate...");
            String rate = facade.getAttributeValueFromJson(
                    "https://api.fxratesapi.com/latest?base=EUR&currencies=USD",
                    "base"
            );
            System.out.println("✓ Success!");
            System.out.println("Base Currency: " + rate);

            System.out.println("\nFetching rates object...");
            String ratesObj = facade.getAttributeValueFromJson(
                    "https://api.fxratesapi.com/latest?base=EUR&currencies=USD",
                    "date"
            );
            System.out.println("✓ Success!");
            System.out.println("Rate Date: " + ratesObj);
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Demonstrates error handling when the URL is invalid.
     */
    private static void demonstrateInvalidUrl(ApiFacade facade) {
        try {
            System.out.println("Attempting to access invalid URL: 'not a valid url'...");
            facade.getAttributeValueFromJson("not a valid url", "value");
        } catch (IOException e) {
            System.out.println("✓ Correctly caught IOException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Unexpected IllegalArgumentException: " + e.getMessage());
        }
    }

    /**
     * Demonstrates error handling when the requested attribute is not found.
     */
    private static void demonstrateAttributeNotFound(ApiFacade facade) {
        try {
            System.out.println("Attempting to extract non-existent attribute 'nonexistent'...");
            facade.getAttributeValueFromJson(
                    "https://api.chucknorris.io/jokes/random",
                    "nonexistent"
            );
        } catch (IOException e) {
            System.out.println("✗ Unexpected IOException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly caught IllegalArgumentException: " + e.getMessage());
        }
    }
}