public class Book {
    private String title;
    private String author;

    public Book(String inTitle, String inAuthor) {
        title = inTitle;
        author = inAuthor;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean equals(Book book) {
        if (title.equals(book.getTitle()) && author.equals(book.getAuthor())) {
            return true;
        }
        else {
            return false;
        }
    }

    public String  toString() {
        return title + ", written by " + author;
    }
}
