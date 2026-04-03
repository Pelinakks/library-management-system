import Library.system.LibrarySystem;
import Library.model.Book;
import Library.model.Member;

public class Main {
    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();

        System.out.println("=== INITIAL BOOKS (Student ID Based) ===");
        System.out.println("Book count initialized by Student ID");
        System.out.println();

        // ----------------------------------------

        System.out.println("=== REGISTER MEMBERS ===");
        Member yunus = new Member(101, "Yunus");
        Member pelin = new Member(102, "Pelin");

        system.registerMember(yunus);
        system.registerMember(pelin);
        System.out.println("Members registered");
        System.out.println();

        // ----------------------------------------

        System.out.println("=== SEARCH TEST (BST) ===");
        system.addBook(new Book(10, "Clean Code", "Robert Martin"));
        system.addBook(new Book(11, "Algorithms", "CLRS"));
        system.addBook(new Book(12, "Design Patterns", "GoF"));
        system.addBook(new Book(13, "Effective Java", "Joshua Bloch"));
        system.addBook(new Book(14, "Refactoring", "Martin Fowler"));

        System.out.println("Books added for search test");
        System.out.println();

        Book searched1 = system.searchBookByTitle("Algorithms");
        System.out.println(searched1 != null
                ? "Found: " + searched1.getTitle()
                : "Not found");

        Book searched2 = system.searchBookByTitle("Refactoring");
        System.out.println(searched2 != null
                ? "Found: " + searched2.getTitle()
                : "Not found");

        Book searched3 = system.searchBookByTitle("Non Existing Book");
        System.out.println(searched3 != null
                ? "Found: " + searched3.getTitle()
                : "Not found");

        System.out.println();

        // ----------------------------------------

        System.out.println("=== BORROW SYSTEM TEST ===");
          system.borrowBook(101, 10); // Clean Code
          system.borrowBook(101, 11); // Algorithms
          system.borrowBook(101, 12); // Design Patterns

          system.borrowBook(102, 12); // waitlist
          system.borrowBook(102, 10); // waitlist

          System.out.println();

        // ----------------------------------------

        System.out.println("=== RETURN + WAITLIST TEST ===");
        system.returnBook(101, 12);
        system.returnBook(101, 10);

        System.out.println();

        // ----------------------------------------

        System.out.println("=== UNDO TEST ===");
        system.undo();
        System.out.println();

        // ----------------------------------------

        System.out.println("=== POPULAR BOOK TEST (HEAP) ===");
        Book popular = system.getMostPopularBook();
        if (popular != null) {
            System.out.println("Most popular book: "
                    + popular.getTitle()
                    + " (Borrowed "
                    + popular.getBorrowCount()
                    + " times)");
        }
        System.out.println();
        // ----------------------------------------

        System.out.println("=== MEMBER BORROWED BOOKS ===");
        System.out.println("Yunus borrowed:");
        yunus.printBorrowedBooks();
        System.out.println();

        System.out.println("Pelin borrowed:");
        pelin.printBorrowedBooks();
    }
}