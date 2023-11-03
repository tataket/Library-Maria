import java.util.ArrayList;

public class Book {
    private static int countBooks = 0;
    String nameBook;
    String author;
    int stock;
    private int iD = 0;
    static ArrayList<String> userWithBook;
    User borrowed;

    public Book(String name, String author, int stock) {
        this.nameBook = name;
        this.author = author;
        this.stock = stock;
        this.borrowed = null;
        Library.books.add(this);
        this.iD = countBooks;
        countBooks++;
    }

    public String getNameBook() {
        return this.nameBook;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getStock() {
        return this.stock;
    }

    public int getiD() {
        return iD;
    }

    public String getUserWithBook() {
        return this.borrowed == null ? "No user contains this book" : this.borrowed.userName;
    }

    public void loanBook(User user) {
        if (stock > 0) {
            this.borrowed = user;
            user.bookInventory.add(this);
            stock--;
            System.out.println("The book was borrowed.......");
        } else {
            System.out.println("There is not stock available........");
        }
    }

    public void returnBook(User user) {
        if (this.borrowed.userName.equals(user.getName())) {
            this.borrowed = null;
            user.bookInventory.remove(this);
            stock++;
        } else {
            System.out.println("You have no  books to return!");
        }
    }

}


