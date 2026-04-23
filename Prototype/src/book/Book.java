package book;

public class Book implements Cloneable {

    private String title;
    private String author;
    private String genre;
    private int publicationYear;

    public Book(String title, String author, String genre, int publicationYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    @Override
    public Book clone() {
        try {
            // All fields are primitives or immutable Strings, so super.clone() is a deep copy here
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Book cloning failed", e);
        }
    }

    // Getters
    public String getTitle()         { return title; }
    public String getAuthor()        { return author; }
    public String getGenre()         { return genre; }
    public int getPublicationYear()  { return publicationYear; }

    // Setters (used after cloning to modify individual books)
    public void setTitle(String title)               { this.title = title; }
    public void setAuthor(String author)             { this.author = author; }
    public void setGenre(String genre)               { this.genre = genre; }
    public void setPublicationYear(int year)         { this.publicationYear = year; }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s (%s, %d)", title, author, genre, publicationYear);
    }
}
