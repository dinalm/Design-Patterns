/**
 * Represents a user of the document library.
 * In a real application, construction would involve authentication.
 */
public class User {
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User(" + username + ")";
    }
}