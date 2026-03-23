/**
 * Visitor that accumulates the total size of all files in the file system.
 * Directories themselves have no size — only files contribute.
 */
public class SizeCalculatorVisitor implements FileSystemVisitor {

    private double totalSizeMB = 0;
    private int fileCount = 0;

    @Override
    public void visitFile(File file) {
        totalSizeMB += file.getSizeMB();
        fileCount++;
    }

    @Override
    public void visitDirectory(Directory directory) {
        // Directories carry no size; traversal is handled by Directory.accept()
    }

    public double getTotalSizeMB() {
        return totalSizeMB;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void printReport() {
        System.out.printf("  Total: %d file(s), %.2f MB%n", fileCount, totalSizeMB);
    }
}