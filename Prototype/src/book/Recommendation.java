package book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recommendation implements Cloneable {

    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    @Override
    public Recommendation clone() {
        try {
            Recommendation cloned = (Recommendation) super.clone();
            // Deep copy the book list — each Book is also cloned
            cloned.books = new ArrayList<>();
            for (Book book : this.books) {
                cloned.books.add(book.clone());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Recommendation cloning failed", e);
        }
    }

    // --- Modification methods ---

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String title) {
        return books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    // --- Accessors ---

    public String getTargetAudience() {
        return targetAudience;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public int getBookCount() {
        return books.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Target Audience: ").append(targetAudience).append("\n");
        if (books.isEmpty()) {
            sb.append("  (no books yet)");
        } else {
            for (int i = 0; i < books.size(); i++) {
                sb.append("  ").append(i + 1).append(". ").append(books.get(i)).append("\n");
            }
        }
        return sb.toString().stripTrailing();
    }
}
