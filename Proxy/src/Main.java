import java.time.LocalDate;

/**
 * Demonstrates the protection proxy pattern with various users,
 * protected and unprotected documents, and access control scenarios.
 */
public class Main {

    public static void main(String[] args) {

        // ── Setup ─────────────────────────────────────────────────────────────

        AccessControlService acs = AccessControlService.getInstance();
        Library library = new Library();

        // Unprotected documents — anyone can read them
        library.addDocument("DOC-001", LocalDate.of(2024, 1, 15),
                "Public annual report: Revenue grew 12% year-on-year.");

        library.addDocument("DOC-002", LocalDate.of(2024, 3, 22),
                "Open-source license agreement v3.0.");

        // Protected documents — access granted per user below
        library.addProtectedDocument("DOC-003", LocalDate.of(2024, 6, 5),
                "Confidential: Q3 financial projections and M&A targets.");

        library.addProtectedDocument("DOC-004", LocalDate.of(2024, 9, 10),
                "HR record: Performance review for employee #4821.");

        library.addProtectedDocument("DOC-005", LocalDate.of(2024, 11, 1),
                "Top secret: Encryption keys for production servers.");

        // Grant access: alice can read DOC-003 and DOC-004; bob can read DOC-003 only
        acs.grantAccess("alice", "DOC-003");
        acs.grantAccess("alice", "DOC-004");
        acs.grantAccess("bob",   "DOC-003");

        // Users
        User alice = new User("alice");
        User bob   = new User("bob");
        User carol = new User("carol");  // no special permissions

        // ── Scenarios ─────────────────────────────────────────────────────────

        System.out.println("=== Protected Document System Demo ===\n");

        // 1. Any user can read unprotected documents
        System.out.println("--- Scenario 1: Unprotected documents (public) ---");
        tryRead(alice, library, "DOC-001");
        tryRead(carol, library, "DOC-001");
        tryRead(bob,   library, "DOC-002");

        // 2. Creation dates are always accessible, even on protected documents
        System.out.println("\n--- Scenario 2: Creation dates are always public ---");
        for (String id : new String[]{"DOC-001", "DOC-003", "DOC-005"}) {
            DocumentInterface doc = library.getDocument(id);
            System.out.printf("  %s — created: %s%n", id, doc.getCreationDate());
        }

        // 3. Allowed users can access protected content
        System.out.println("\n--- Scenario 3: Allowed access to protected documents ---");
        tryRead(alice, library, "DOC-003");
        tryRead(alice, library, "DOC-004");
        tryRead(bob,   library, "DOC-003");

        // 4. Denied users receive AccessDeniedException
        System.out.println("\n--- Scenario 4: Denied access to protected documents ---");
        tryRead(bob,   library, "DOC-004");  // bob not granted DOC-004
        tryRead(carol, library, "DOC-003");  // carol has no permissions
        tryRead(carol, library, "DOC-005");  // nobody granted DOC-005

        // 5. Revoke access and verify
        System.out.println("\n--- Scenario 5: Revoke alice's access to DOC-003 ---");
        System.out.println("  [Before revocation]");
        tryRead(alice, library, "DOC-003");
        acs.revokeAccess("alice", "DOC-003");
        System.out.println("  [After revocation]");
        tryRead(alice, library, "DOC-003");

        // 6. Grant new access at runtime
        System.out.println("\n--- Scenario 6: Grant carol access to DOC-005 at runtime ---");
        tryRead(carol, library, "DOC-005");  // denied first
        acs.grantAccess("carol", "DOC-005");
        tryRead(carol, library, "DOC-005");  // now allowed
    }

    /**
     * Helper: attempts to read a document's content and prints the result.
     */
    private static void tryRead(User user, Library library, String docId) {
        DocumentInterface doc = library.getDocument(docId);
        if (doc == null) {
            System.out.printf("  [%s] Document '%s' not found.%n", user.getUsername(), docId);
            return;
        }
        try {
            String content = doc.getContent(user);
            System.out.printf("  [%s] %s → \"%s\"%n", user.getUsername(), docId, content);
        } catch (AccessDeniedException e) {
            System.out.printf("  [%s] %s → ACCESS DENIED: %s%n",
                    user.getUsername(), docId, e.getMessage());
        }
    }
}