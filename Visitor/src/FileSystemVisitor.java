/**
 * Visitor interface declaring a visit method for each concrete element type.
 */
public interface FileSystemVisitor {
    void visitFile(File file);
    void visitDirectory(Directory directory);
}