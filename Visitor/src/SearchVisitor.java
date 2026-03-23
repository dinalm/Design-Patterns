import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor implements FileSystemVisitor {

    private final Predicate<File> criterion;
    private final String criterionDescription;
    private final List<File> matches = new ArrayList<>();

    public SearchVisitor(Predicate<File> criterion, String criterionDescription) {
        this.criterion = criterion;
        this.criterionDescription = criterionDescription;
    }

    // Factory methods for common search types

    /** Matches files whose name ends with the given extension (e.g. ".pdf"). */
    public static SearchVisitor byExtension(String extension) {
        String ext = extension.startsWith(".") ? extension : "." + extension;
        return new SearchVisitor(
                f -> f.getName().toLowerCase().endsWith(ext.toLowerCase()),
                "extension = " + ext
        );
    }

    /** Matches files whose name contains the given substring (case-insensitive). */
    public static SearchVisitor byNameContains(String substring) {
        return new SearchVisitor(
                f -> f.getName().toLowerCase().contains(substring.toLowerCase()),
                "name contains \"" + substring + "\""
        );
    }

    /** Matches files larger than the given size in MB. */
    public static SearchVisitor largerThan(double minSizeMB) {
        return new SearchVisitor(
                f -> f.getSizeMB() > minSizeMB,
                "size > " + minSizeMB + " MB"
        );
    }

    // Visitor methods

    @Override
    public void visitFile(File file) {
        if (criterion.test(file)) {
            matches.add(file);
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        // No action on directories; traversal is handled by Directory.accept()
    }

    // Results

    public List<File> getMatches() {
        return matches;
    }

    public void printReport() {
        System.out.println("  Search criterion: " + criterionDescription);
        if (matches.isEmpty()) {
            System.out.println("  No files found.");
        } else {
            for (File f : matches) {
                System.out.println("    - " + f);
            }
            System.out.println("  Found: " + matches.size() + " file(s)");
        }
    }
}