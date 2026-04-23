package book;

import java.util.Scanner;

public class Main {

    private static final RecommendationManager manager = new RecommendationManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     Book Recommendations System      ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> viewAll();
                case "2" -> createNew();
                case "3" -> cloneAndModify();
                case "4" -> addBookToList();
                case "5" -> removeBookFromList();
                case "6" -> deleteList();
                case "0" -> { System.out.println("Goodbye!"); running = false; }
                default  -> System.out.println("  Invalid option. Try again.");
            }
        }
    }

    // ── Menu ────────────────────────────────────────────────────────────────

    private static void printMainMenu() {
        System.out.println("\n─────────────────────────────────────");
        System.out.println("  1. View all recommendation lists");
        System.out.println("  2. Create new recommendation list");
        System.out.println("  3. Clone & modify an existing list");
        System.out.println("  4. Add a book to a list");
        System.out.println("  5. Remove a book from a list");
        System.out.println("  6. Delete a recommendation list");
        System.out.println("  0. Exit");
        System.out.print("  Choice: ");
    }

    // ── Actions ─────────────────────────────────────────────────────────────

    private static void viewAll() {
        if (manager.isEmpty()) {
            System.out.println("\n  No recommendation lists saved yet.");
            return;
        }
        System.out.println();
        for (int i = 0; i < manager.getAll().size(); i++) {
            System.out.println("  [" + (i + 1) + "] " + manager.get(i + 1).getTargetAudience());
            for (String line : manager.get(i + 1).toString().split("\n")) {
                System.out.println("      " + line);
            }
            System.out.println();
        }
    }

    private static void createNew() {
        System.out.print("  Target audience name: ");
        String audience = scanner.nextLine().trim();
        if (audience.isEmpty()) { System.out.println("  Cancelled."); return; }

        Recommendation rec = new Recommendation(audience);
        System.out.println("  Add books now? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            addBooksInteractively(rec);
        }
        manager.save(rec);
        System.out.println("  ✓ Saved: " + audience + " (" + rec.getBookCount() + " book(s))");
    }

    private static void cloneAndModify() {
        if (manager.isEmpty()) { System.out.println("  No lists to clone."); return; }
        viewAll();
        System.out.print("  Clone list number: ");
        Integer idx = readInt();
        if (idx == null) return;

        Recommendation clone = manager.cloneRecommendation(idx);
        if (clone == null) { System.out.println("  Invalid number."); return; }

        System.out.println("  Cloned from: " + clone.getTargetAudience());
        System.out.print("  New target audience (Enter to keep \"" + clone.getTargetAudience() + "\"): ");
        String newAudience = scanner.nextLine().trim();
        if (!newAudience.isEmpty()) clone.setTargetAudience(newAudience);

        System.out.println("  Would you like to add books to the clone? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            addBooksInteractively(clone);
        }

        System.out.println("  Would you like to remove books from the clone? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            removeBooksInteractively(clone);
        }

        manager.save(clone);
        System.out.println("  ✓ Clone saved: " + clone.getTargetAudience());
    }

    private static void addBookToList() {
        if (manager.isEmpty()) { System.out.println("  No lists available."); return; }
        viewAll();
        System.out.print("  Add book to list number: ");
        Integer idx = readInt();
        if (idx == null) return;
        Recommendation rec = manager.get(idx);
        if (rec == null) { System.out.println("  Invalid number."); return; }
        addBooksInteractively(rec);
    }

    private static void removeBookFromList() {
        if (manager.isEmpty()) { System.out.println("  No lists available."); return; }
        viewAll();
        System.out.print("  Remove book from list number: ");
        Integer idx = readInt();
        if (idx == null) return;
        Recommendation rec = manager.get(idx);
        if (rec == null) { System.out.println("  Invalid number."); return; }
        removeBooksInteractively(rec);
    }

    private static void deleteList() {
        if (manager.isEmpty()) { System.out.println("  No lists to delete."); return; }
        viewAll();
        System.out.print("  Delete list number: ");
        Integer idx = readInt();
        if (idx == null) return;
        if (manager.remove(idx)) {
            System.out.println("  ✓ Deleted.");
        } else {
            System.out.println("  Invalid number.");
        }
    }

    // ── Helpers ─────────────────────────────────────────────────────────────

    private static void addBooksInteractively(Recommendation rec) {
        System.out.println("  Enter books (blank title to stop):");
        while (true) {
            System.out.print("    Title: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) break;

            System.out.print("    Author: ");
            String author = scanner.nextLine().trim();

            System.out.print("    Genre: ");
            String genre = scanner.nextLine().trim();

            System.out.print("    Publication year: ");
            String yearStr = scanner.nextLine().trim();
            int year = 0;
            try { year = Integer.parseInt(yearStr); } catch (NumberFormatException ignored) {}

            rec.addBook(new Book(title, author, genre.isEmpty() ? "Unknown" : genre, year));
            System.out.println("    ✓ Added.");
        }
    }

    private static void removeBooksInteractively(Recommendation rec) {
        if (rec.getBookCount() == 0) {
            System.out.println("  No books in this list.");
            return;
        }
        System.out.println("  Books in list:");
        rec.getBooks().forEach(b -> System.out.println("    - " + b.getTitle()));
        System.out.print("  Enter exact title to remove (blank to cancel): ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) return;
        if (rec.removeBook(title)) {
            System.out.println("  ✓ Removed \"" + title + "\".");
        } else {
            System.out.println("  Book not found.");
        }
    }

    private static Integer readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  Please enter a valid number.");
            return null;
        }
    }

    // ── Sample data ─────────────────────────────────────────────────────────

    private static void seedSampleData() {
        Recommendation teens = new Recommendation("Young Adults");
        teens.addBook(new Book("The Hunger Games", "Suzanne Collins", "Dystopian", 2008));
        teens.addBook(new Book("Percy Jackson", "Rick Riordan", "Fantasy", 2005));
        manager.save(teens);

        Recommendation sciFi = new Recommendation("Sci-Fi Enthusiasts");
        sciFi.addBook(new Book("Dune", "Frank Herbert", "Science Fiction", 1965));
        sciFi.addBook(new Book("Neuromancer", "William Gibson", "Cyberpunk", 1984));
        sciFi.addBook(new Book("The Martian", "Andy Weir", "Science Fiction", 2011));
        manager.save(sciFi);
    }
}
