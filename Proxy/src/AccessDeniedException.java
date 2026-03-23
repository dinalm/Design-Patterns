/**
 * Thrown when a user attempts to access a protected document without permission.
 */
public class AccessDeniedException extends Exception {
    public AccessDeniedException(String username, String documentId) {
        super("Access denied: user '" + username + "' is not allowed to access document '" + documentId + "'.");
    }
}