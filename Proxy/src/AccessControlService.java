import java.util.HashSet;
import java.util.Set;

/**
 * Singleton service that manages access control for protected documents.
 * Stores (username, documentId) pairs to represent granted permissions.
 */
public class AccessControlService {

    private static AccessControlService instance;

    // Each entry is "username:documentId"
    private final Set<String> allowedPairs = new HashSet<>();

    private AccessControlService() {}

    public static AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }

    /**
     * Grants a user access to a specific document.
     */
    public void grantAccess(String username, String documentId) {
        allowedPairs.add(key(username, documentId));
    }

    /**
     * Revokes a user's access to a specific document.
     */
    public void revokeAccess(String username, String documentId) {
        allowedPairs.remove(key(username, documentId));
    }

    /**
     * Returns true if the user is allowed to access the document.
     */
    public boolean isAllowed(String documentId, String username) {
        return allowedPairs.contains(key(username, documentId));
    }

    private String key(String username, String documentId) {
        return username + ":" + documentId;
    }
}