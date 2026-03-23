import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system.
 * Can contain any mix of Files and sub-Directories.
 */
public class Directory implements FileSystemElement {

    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void add(FileSystemElement element) {
        children.add(element);
    }

    public List<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * First visits this directory, then propagates the visitor to all children.
     * This drives the recursive traversal of the entire tree.
     */
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
        for (FileSystemElement child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public String toString() {
        return name + "/";
    }
}