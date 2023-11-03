import java.util.ArrayList;

public class User {
    String userName;
    String password;
    Boolean admin;
    private static int iD = 0;
    ArrayList<Book> bookInventory;

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
        this.bookInventory = new ArrayList<>();
        iD++;
        if (userName.contains("#")) {
            this.admin = true;
            System.out.println("Congrats account created, " + userName + " has admin permission.");
        } else {
            this.admin = false;
            System.out.println("Congrats account created.");
        }
        Main.users.add(this);
    }

    public String getName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "User: " + userName + ", " + password + ".";
    }


}

