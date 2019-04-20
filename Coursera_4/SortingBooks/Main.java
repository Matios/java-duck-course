
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Main {
     public void testCompareBooks() {
        Book b1 = new Book("Rowling", 123, 4.3);
        Book b2 = new Book("Tolkien", 423, 4.2);

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(b1);
        books.add(b2);
        ToggleAuthorSort sort = new ToggleAuthorSort();
        Collections.sort(books, sort);
        System.out.println(books);
        Collections.sort(books, sort);
        System.out.println(books);
        Collections.sort(books, sort);
        sort = new ToggleAuthorSort();
        System.out.println(books);
        Collections.sort(books, sort);
        System.out.println(books);
    }
    
    public void makeStuff(){
        Book b1 = new Book("Rowling", 123, 4.3);
        Book b2 = new Book("Tolkien", 423, 4.2);
        Book b3 = new Book("Chabon", 98, 2.4);
        Book b4 = new Book("Vonnegut", 240, 3.4);
        Book b5 = new Book("Dickens", 600, 1.3);
        
        ArrayList<Book> bs = new ArrayList<Book>();
        bs.add(b1);
        bs.add(b2);
        bs.add(b3);
        bs.add(b4);
        bs.add(b5);
        
        Patron p = new Patron("Rayan", bs);
    }
    
     public void testPageCount() {
        Book b1 = new Book("Rowling", 123, 4.3);
        Book b2 = new Book("Tolkien", 423, 4.2);
        Book b3 = new Book("Chabon", 98, 2.4);
        Book b4 = new Book("Vonnegut", 240, 3.4);
        Book b5 = new Book("Dickens", 600, 1.3);
        
        ArrayList<Book> bs = new ArrayList<Book>();
        bs.add(b1);
        bs.add(b2);
        bs.add(b3);
        bs.add(b4);
        bs.add(b5);
        
        ArrayList<Integer> pageCounts = new ArrayList<Integer>();
        
        for(Book b : bs) {
            pageCounts.add(b.getPageCount());
        }
        
        System.out.println("Before sort " + pageCounts);
        System.out.println("After sort " + sortPageCount(pageCounts));

    }
    
    private int getMin(ArrayList<Integer> input) {
        int result = 0;
        int minimum = input.get(0); // 3
        for(int i = 0; i < input.size(); i++) {
            if(input.get(i) < minimum) {
                minimum = input.get(i);
            }
        }
        
        return input.indexOf(minimum);
    }
    public ArrayList<Integer> sortPageCount(ArrayList<Integer> input){
        ArrayList<Integer> result = new ArrayList<Integer>(input);
        //Collections.sort(result);
        while(!input.isEmpty()){
        //1. get the minimum of input
        int min = getMin(input);
        //2. add the minimum to result
        result.add(input.get(min));
        //3. remove the min from input
         input.remove(min);
        //result.add(minimum);
        
        }
        return result;
    }
}
