public class PatronDriver {
    public static void main(String[] args) {
        Patron p1 = new Patron("John Doe");
        Patron p2 = new Patron("Jane");
        Patron p3 = new Patron("Steven");
        Patron p4 = new Patron("Kyle");

        p1.borrow("The Crucible", "Arthur Miller");
        p1.borrow("MacBeth", "Shakespeare");
        p1.borrow("A History of Latin America", "Benjamin Keene");
        p2.borrow("Mathmatics: Analysis and Approaches (Higher Level)", "Jennifer Cheng");
        p2.borrow("Fredrick Douglas: A Narrative", "Fredrick Douglas");
        p3.borrow("Art of Racing in the Rain", "Garth Stein");

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());

        p2.returnBook("Fredrick Douglas: A Narrative");
        p1.returnBook("This isn't a book");
        p4.borrow("A History of Latin America", "Benjamin Keene");
        p1.borrow("AMSCO", "John Callahan");

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());
    }

}
