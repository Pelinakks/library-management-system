package Library.model;
import Library.datastructures.SimpleLinkedList;
public class Member {
    private int id;
    private String name;
    private SimpleLinkedList<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new SimpleLinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        // Adds the book to the member's current borrowed books list
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        // Removes the book from the member's borrowed books list
    }

    public void printBorrowedBooks() {
        borrowedBooks.printList();
    }

}
