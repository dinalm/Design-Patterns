public class Main {

    public static void main(String[] args) {

        // Build the file system tree

        Directory root = new Directory("root");
        root.add(new File("resume.pdf",  0.3));
        root.add(new File("photo.jpg",   4.5));
        root.add(new File("notes.txt",   0.1));

        Directory documents = new Directory("documents");
        documents.add(new File("report.pdf",       2.1));
        documents.add(new File("budget.xlsx",      0.8));
        documents.add(new File("notes_final.txt",  0.2));
        root.add(documents);

        Directory backup = new Directory("backup");
        backup.add(new File("old_photo.jpg", 3.1));
        backup.add(new File("archive.zip",   120.0));

        Directory media = new Directory("media");
        media.add(new File("video.mp4", 700.0));
        media.add(new File("song.mp3",    5.2));
        media.add(backup);
        root.add(media);

        System.out.println("=== File System Visitor Demo ===\n");

        // SizeCalculatorVisitor

        System.out.println("--- Size of entire tree (root/) ---");
        SizeCalculatorVisitor sizeAll = new SizeCalculatorVisitor();
        root.accept(sizeAll);
        sizeAll.printReport();

        System.out.println("\n--- Size of documents/ only ---");
        SizeCalculatorVisitor sizeDocs = new SizeCalculatorVisitor();
        documents.accept(sizeDocs);
        sizeDocs.printReport();

        System.out.println("\n--- Size of media/ subtree ---");
        SizeCalculatorVisitor sizeMedia = new SizeCalculatorVisitor();
        media.accept(sizeMedia);
        sizeMedia.printReport();

        // SearchVisitor: by extension

        System.out.println("\n--- Search: all .pdf files ---");
        SearchVisitor pdfSearch = SearchVisitor.byExtension(".pdf");
        root.accept(pdfSearch);
        pdfSearch.printReport();

        System.out.println("\n--- Search: all .jpg files ---");
        SearchVisitor jpgSearch = SearchVisitor.byExtension(".jpg");
        root.accept(jpgSearch);
        jpgSearch.printReport();

        // SearchVisitor: by name substring

        System.out.println("\n--- Search: files whose name contains \"notes\" ---");
        SearchVisitor notesSearch = SearchVisitor.byNameContains("notes");
        root.accept(notesSearch);
        notesSearch.printReport();

        // SearchVisitor: by size threshold

        System.out.println("\n--- Search: files larger than 100 MB ---");
        SearchVisitor largeSearch = SearchVisitor.largerThan(100.0);
        root.accept(largeSearch);
        largeSearch.printReport();

        // SearchVisitor: no results case

        System.out.println("\n--- Search: all .py files (none exist) ---");
        SearchVisitor pySearch = SearchVisitor.byExtension(".py");
        root.accept(pySearch);
        pySearch.printReport();
    }
}
