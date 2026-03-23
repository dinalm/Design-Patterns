import java.time.LocalDate;

/**
 * Common interface for both real Document objects and DocumentProxy objects.
 * Enables the proxy to be used transparently in place of a real document.
 */
public interface DocumentInterface {
    String getId();
    LocalDate getCreationDate();
    String getContent(User user) throws AccessDeniedException;
}
