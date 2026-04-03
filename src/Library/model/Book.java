package Library.model;

import java.util.LinkedList;
import java.util.Queue;
import Library.datastructures.SimpleQueue;

 /**
  Setter methods are deliberately avoided for certain fields in order to preserve
  encapsulation and prevent invalid state changes. State transitions are handled
  through domain-specific methods such as borrow() and giveBack().
 */

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;
    private int borrowCount;
    private SimpleQueue<Member> waitList = new SimpleQueue<>();

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
        this.borrowCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

     // Marks the book as borrowed and increments borrow count
     //This method is the only valid way to change a book's availability
    public void borrow() {
        available = false;
        borrowCount++; //book has been borrowed one more time, this will be used in popular books
    }

    public void giveBack() {
        available = true;
        //our book is being brought back
        //it's on the shelf again so it's available
    }

    public int getBorrowCount() {
        return borrowCount;
        //important for most popular book
    }

    public boolean hasWaitList() {
        return !waitList.isEmpty();
    }

    public void addToWaitList(Member member) {
        waitList.enqueue(member);
    }

    public Member getNextFromWaitList() {
        return waitList.dequeue();
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "'}";
    }
}
