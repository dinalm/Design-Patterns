import java.time.LocalDate;

/**
 * Real document object. Has package-private visibility so it cannot be
 * instantiated directly from outside this package — protected documents
 * must be created and accessed through DocumentProxy.
 *
 * Unprotected documents are exposed directly via the DocumentInterface.
 */
class Document implements DocumentInterface {

    private final String id;
    private final LocalDate creationDate;
    private final String content;

    Document(String id, LocalDate creationDate, String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * For unprotected documents, content is accessible to anyone.
     * For protected documents this method is never called directly —
     * only the DocumentProxy calls it after an access control check.
     */
    @Override
    public String getContent(User user) {
        return content;
    }
}