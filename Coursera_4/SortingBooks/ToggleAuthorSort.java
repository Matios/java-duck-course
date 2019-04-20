import java.util.*;

public class ToggleAuthorSort implements Comparator<Book> {
    private boolean direction;
    public ToggleAuthorSort() {
        direction = true;
    }
    public int compare(Book a, Book b) {
        if (direction) {
            direction = !direction;
            return a.getAuthor().compareTo(b.getAuthor());
        } else {
            direction = !direction;
            return b.getAuthor().compareTo(a.getAuthor());
        }
    }
}

