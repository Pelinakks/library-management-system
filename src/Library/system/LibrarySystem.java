package Library.system;
import Library.model.Book;
import Library.model.Member;
import java.util.HashMap;
import Library.datastructures.SimpleStack;
import Library.datastructures.BookSearchTree;
import java.util.PriorityQueue;
import java.util.Comparator;


public class LibrarySystem {
    private HashMap<Integer, Book> books;
    private HashMap<Integer, Member> members;
    private SimpleStack<Action> undoStack = new SimpleStack<>();
    private BookSearchTree searchTree = new BookSearchTree();
    private PriorityQueue<Book> popularBooks;
    private static final int STUDENT_ID = 230315027;


    public LibrarySystem() {
        books = new HashMap<>();
        members = new HashMap<>();
        undoStack = new SimpleStack<>();

        popularBooks = new PriorityQueue<>((b1, b2) -> Integer.compare(b2.getBorrowCount(), b1.getBorrowCount()));

        int initialBookCount = STUDENT_ID % 5 + 3;

        for (int i = 1; i <= initialBookCount; i++) {
            Book book = new Book(
                    i,
                    "Book " + i,
                    "Author " + i
            );
            addBook(book);
        }

    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
        searchTree.insert(book);
        popularBooks.add(book);
    }

    public void registerMember(Member member) {
        members.put(member.getId(), member);
    }

    public Book findBook(int bookId) {
        return books.get(bookId);
    }

    public Member findMember(int memberId) {
        return members.get(memberId);
    }

    public void borrowBook(int memberId, int bookId) {
        //Searching for the book and member in the system
        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member == null) {
            System.out.println("Member not found");
            return;
        }

        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        if (book.isAvailable()) {
            book.borrow();//book state has changed
            member.borrowBook(book);//added to members list

            popularBooks.remove(book);
            popularBooks.add(book);

            undoStack.push(new Action(Action.ActionType.BORROW, member, book));

            System.out.println("Book borrowed successfully");
        } else {
            book.addToWaitList(member);
            System.out.println("Book is not available. Member added to waitlist.");
        }
    }

    public void returnBook(int memberId, int bookId) {
        // Handles return logic and assigns the book to the next
        // member in the waitlist if applicable
        Member member = members.get(memberId);
        Book book = books.get(bookId);

        if (member == null || book==null) {
            System.out.println("Book or Member not found");
            return;
        }

        book.giveBack();
        member.returnBook(book);

        undoStack.push(new Action(Action.ActionType.RETURN,member, book));


        if (book.hasWaitList()) {
            Member nextMember = book.getNextFromWaitList();
            book.borrow();
            nextMember.borrowBook(book);
            System.out.println("Book automatically assigned to next member in waitlist");
        } else {
            System.out.println("Book returned and is now available");
        }
    }

    public void undo() {

        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }

        Action lastAction = undoStack.pop();

        if (lastAction.type == Action.ActionType.BORROW) {
            // Borrow undo
            lastAction.book.giveBack();
            lastAction.member.returnBook(lastAction.book);
            System.out.println("Undo: Borrow operation reverted");

        } else if (lastAction.type == Action.ActionType.RETURN) {
            // Return undo
            lastAction.book.borrow();
            lastAction.member.borrowBook(lastAction.book);
            System.out.println("Undo: Return operation reverted");
        }
    }

    public Book searchBookByTitle(String title) {
        return searchTree.searchByTitle(title);
    }

    public Book getMostPopularBook() {
        return popularBooks.peek();
    }


}
