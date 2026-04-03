package Library.datastructures;
import Library.model.Book;

public class BookSearchTree {
    private Node root;
    private static class Node {
        Book book;
        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
        }
    }
   //kitap ekleme
    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private Node insertRec(Node current, Book book) {
        if (current == null) {
            return new Node(book);
        }
        int compare = book.getTitle().compareTo(current.book.getTitle());

        if (compare < 0) {
            current.left = insertRec(current.left, book);
        } else if (compare > 0) {
            current.right = insertRec(current.right, book);
        }
        // eşitse bir şey yapmıyoruz (aynı başlık)

        return current;
    }

    public Book searchByTitle(String title) {
        return searchRec(root, title);
    }

    private Book searchRec(Node current, String title) {
        if (current == null) {
            return null;
        }
        int compare = title.compareTo(current.book.getTitle());
        if (compare == 0) {
            return current.book;
        } else if (compare < 0) {
            return searchRec(current.left, title);
        } else {
            return searchRec(current.right, title);
        }
    }
}

