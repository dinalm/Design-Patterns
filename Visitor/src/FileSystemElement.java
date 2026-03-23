/**
 * Base interface for all file system elements.
 */
public interface FileSystemElement {
    String getName();
    void accept(FileSystemVisitor visitor);
}