public class Patron {
    private String name;
    private Book book1;
    private Book book2;
    private Book book3;

    public Patron(String inName) {
        name = inName;
        book1 = null;
        book2 = null;
        book3 = null;
    }

    private boolean check(String inTitle, String inAuthor) {
        Book temp = new Book(inTitle, inAuthor);
        if (book1 != null && book1.equals(temp)) {
            return true;
        }
        if (book2 != null && book2.equals(temp)) {
            return true;
        }
        if (book3 != null && book3.equals(temp)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void returnBook(String inTitle) {
        if (book1 != null && book1.getTitle().equals(inTitle)) {
            book1 = null;
        }
        else if (book2 != null && book2.getTitle().equals(inTitle)) {
            book2 = null;
        }
        else if (book3 != null && book3.getTitle().equals(inTitle)) {
            book3 = null;
        }
        else {
            System.out.println("The book with the specified title is not checked out to this user.");
        }
    }

    public void borrow(String inTitle, String inAuthor) {
        if (check(inTitle, inAuthor)) {
            System.out.println("Checkout unsuccessful. You already have this book checked out.");
        }
        else if (book1 == null) {
            book1 = new Book(inTitle, inAuthor);
        }
        else if (book2 == null) {
            book2 = new Book(inTitle, inAuthor);
        }
        else if (book3 == null) {
            book3 = new Book(inTitle, inAuthor);
        }
        else {
            System.out.println("Checkout unsuccessful. You already have three books checked out.");
        }
    }

    public String toString() {
        int i = 0;
        if (book1 != null) {
            i += 1;
        }
        if (book2 != null) {
            i += 2;
        }
        if (book3 != null) {
            i += 4;
        }
        switch(i) {
            case 1:
                return book1.getTitle() + " is checked out to " + name + ".";
            case 2:
                return book2.getTitle() + " is checked out to " + name + ".";
            case 3:
                return book1.getTitle() + " and " + book2.getTitle() + " are checked out to " + name + ".";
            case 4:
                return book3.getTitle() + " is checked out to " + name + ".";
            case 5:
                return book1.getTitle() + " and " + book3.getTitle() + " are checked out to " + name + ".";
            case 6:
                return book2.getTitle() + " and " + book3.getTitle() + " are checked out to " + name + ".";
            case 7:
                return book1.getTitle() + ", " + book2.getTitle() + ", and " + book3.getTitle() + " are checked out to " + name + ".";
            default:
                return "No books are checked out to " + name + ".";
        }
    }
}

