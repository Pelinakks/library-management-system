package Library.system;

import Library.model.Book;
import Library.model.Member;

public class Action {
    public enum ActionType {
        BORROW,
        RETURN

        //enum:limited options list
        //not be able to do syntax error
    }

    public ActionType type;
    public Member member;
    public Book book;

    public Action(ActionType type, Member member, Book book) {
        this.type = type;
        this.member = member;
        this.book = book;
    }
}
