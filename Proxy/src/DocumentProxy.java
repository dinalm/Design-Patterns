import java.time.LocalDate;

/**
 * Protection proxy for a Document.
 * The real Document object is held privately and can only be accessed
 * after the AccessControlService confirms the requesting user is permitted.
 */
public class DocumentProxy implements DocumentInterface {

    private final Document realDocument;
    private final AccessControlService accessControlService;

    DocumentProxy(Document realDocument) {
        this.realDocument = realDocument;
        this.accessControlService = AccessControlService.getInstance();
    }

    @Override
    public String getId() {
        return realDocument.getId();
    }

    /**
     * Creation date is always public — no access control check needed.
     */
    @Override
    public LocalDate getCreationDate() {
        return realDocument.getCreationDate();
    }

    /**
     * Returns document content only if the user is permitted.
     * Throws AccessDeniedException otherwise.
     */
    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (accessControlService.isAllowed(realDocument.getId(), user.getUsername())) {
            return realDocument.getContent(user);
        }
        throw new AccessDeniedException(user.getUsername(), realDocument.getId());
    }
}