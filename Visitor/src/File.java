/**
 * Represents a file in the file system.
 * Holds a name and a size in megabytes.
 */
public class File implements FileSystemElement {

    private final String name;
    private final double sizeMB;

    public File(String name, double sizeMB) {
        this.name = name;
        this.sizeMB = sizeMB;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getSizeMB() {
        return sizeMB;
    }

    /**
     * Tells the visitor that this is a File, so visitFile() is called.
     */
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }

    @Override
    public String toString() {
        return name + " (" + sizeMB + " MB)";
    }
}