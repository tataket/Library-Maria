import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();
    static int menuChoice = 1;
    static User login;

    public static void main(String[] args) {

        new Book("Lord of the Rings", "J.R.R.Tolkien", 10);
        new Book("The Hobbit", "J.R.R.Tolkien", 6);
        new Book("Harry Potter", "JK.Rowling", 10);
        new Book("Shadow Falls", "C. C. Hunter", 8);
        new Book("Tiger's Curse", "Colleen Houck", 5);
        new Book("Halo", "Alexandra Adornetto", 3);
        new Book("Bird Box", "Josh Malerman", 4);


        System.out.println("Welcome to world of books...\n When I woke up this morning, I knew who I was, but I think I've changed many times since then.");

        while (menuChoice != 0) {
            System.out.print("\nMenu\n 1- Login\n 2- Show Users\n 3- Show Books\n 4- Borrowed Books\n 5- Return Books\n 6- Add Books\n 0- Exit\nOption:");
            menuChoice = scan.nextInt();

            switch (menuChoice) {
                case 0:
                    System.out.println("Happiness can be found even in the darkest of times, if one only remembers to turn on the light.");
                    break;
                case 1:
                    loginSignUp();
                    break;
                case 2:
                    showUser();
                    break;
                case 3:
                    showBook();
                    break;
                case 4:
                    borrowedBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    createBook(login);
                    break;
                default:
                    System.out.println("Cant be read you request!!!!!");
                    break;
            }
        }
    }

    public static void loginSignUp() {
        System.out.print("1- Sign up\n2- Login\n0- EXIT\nOption:");
        menuChoice = scan.nextInt();
        switch (menuChoice) {
            case 1:
                System.out.println("Create login");
                System.out.print("username: ");
                String name = scan.next();
                System.out.print("password: ");
                String pass = scan.next();
                System.out.println("Login created");
                new User(name, pass);
                System.out.println(users);
                break;
            case 2:
                login();
        }
    }

    public static User login() {
        System.out.print("username: ");
        String name = scan.next();
        System.out.print("password: ");
        String pass = scan.next();
        for (int i = 0; i < users.size(); i++) {
            if (name.equals(users.get(i).userName) && pass.equals(users.get(i).password)) {
                System.out.println("Logging  in!");
                login = users.get(i);
                return users.get(i);
            }
            if (users.size() == 0) {
                System.out.println("NO USERS ON DATA. FIRST CREATE PLEASE!");
            } else {
                System.out.println("LOGIN INFO DISMATCH!");
            }
        }
        return login();
    }

    public static void showUser(){
        System.out.println("\nUsers");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("ID: "  + i + "\nName: " + users.get(i).getName());
        }
    }
    public static void showBook() {
        System.out.println();
        for (int i = 0; i < Library.books.size(); i++) {
            System.out.println("ID:" + Library.books.get(i).getiD() + " || " +
                    "Book: " + Library.books.get(i).nameBook + " || " +
                    "Author: " + Library.books.get(i).author + " ||" +
                    " Stock: " + Library.books.get(i).stock);
        }
    }

    public static void borrowedBook() {
        System.out.println("Which book do you want to borrow?");
        showBook();
        System.out.println("\nEnter book ID");
        int choice = scan.nextInt();
        System.out.println("Enter ID user");
        int choiceId = scan.nextInt();
        User userChosen = users.get(choiceId);
        System.out.println("Enter password user");
        String choicePass = scan.next();

        if (Objects.equals(users.get(choiceId).getPassword(), choicePass)) {
            String selectNameBook = null;
            Book selectLibrary = null;

            selectNameBook = Library.books.get(choice).nameBook;
            int selectInt = Library.books.get(choice).stock;
            System.out.println("Book: " + selectNameBook + " Stock: " + selectInt);
            selectLibrary= Library.books.get(choice);
            selectLibrary.loanBook(userChosen);
            System.out.println("Successfully borrowed book: " + Library.books.get(choice).nameBook + " to " + userChosen);
            showBook();
        } else {
            System.out.println("Nope");
        }
    }

    public static void returnBook() {
            System.out.println("Return a Book");
            showAllUserWithBook();
            System.out.println("Enter the ID of the user: ");
            int numUser = scan.nextInt();
            showBook();
            System.out.println("Enter the ID of the book: ");
            int choiceBook = scan.nextInt();

            if (numUser >= 0 && numUser < users.size() && choiceBook >= 0 && choiceBook < Library.books.size()) {
                User user = users.get(numUser);
                Book book = Library.books.get(choiceBook);

                System.out.println("Enter user's password: ");
                String choicePass = scan.next();

                if (user.getPassword().equals(choicePass)) {
                    book.returnBook(user);
                    showAllUserWithBook();
                } else {
                    System.out.println("Incorrect password. Return book operation failed.");
                }
            } else {
                System.out.println("Invalid user or book ID. Return book operation failed.");
            }
        }

    public static void showAllUserWithBook(){
        System.out.println("Users with books");
        for (int i = 0; i < Library.books.size(); i++) {
            System.out.print("ID:" + Library.books.get(i).getiD() +
                    " - Book: " + Library.books.get(i).getNameBook() +
                    " - Users: " + Library.books.get(i).getUserWithBook() + "\n");
        }
    }

    public static void createBook(User user) {
        if (user.admin) {
            System.out.print("Book name:");
            scan.nextLine();
            String name = scan.nextLine();
            System.out.print("Author:");
            String author = scan.nextLine();
            System.out.print("Stock:");
            int stock = scan.nextInt();
            new Book(name, author, stock);
            System.out.println("Book " + name + " created!");
        } else {
            System.out.println("Only admins can create books.");
        }
    }
}