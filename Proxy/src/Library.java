import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores the collection of documents (both protected and unprotected).
 * Uses DocumentInterface so it can hold real Document objects and proxies alike.
 *
 * Factory methods handle the construction of protected documents, ensuring
 * the real Document is never exposed directly outside this class.
 */
public class Library {

    private final Map<String, DocumentInterface> documents = new HashMap<>();

    /**
     * Adds an unprotected document accessible to any user.
     */
    public void addDocument(String id, LocalDate creationDate, String content) {
        Document doc = new Document(id, creationDate, content);
        documents.put(id, doc);
    }

    /**
     * Factory method: creates a protected document and wraps it in a proxy.
     * The real Document is never stored or returned directly.
     */
    public void addProtectedDocument(String id, LocalDate creationDate, String content) {
        Document realDoc = new Document(id, creationDate, content);
        DocumentProxy proxy = new DocumentProxy(realDoc);
        documents.put(id, proxy);
    }

    /**
     * Retrieves a document (or its proxy) by ID.
     * Returns null if not found.
     */
    public DocumentInterface getDocument(String id) {
        return documents.get(id);
    }

    /**
     * Returns all document entries for iteration.
     */
    public Map<String, DocumentInterface> getAllDocuments() {
        return documents;
    }
}