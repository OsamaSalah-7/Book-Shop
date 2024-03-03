import java.util.ArrayList;

public class User {

    private String id;
    private String password;
    private String name;
    private String email;
    private double balance;
    private int type;
    ArrayList<Item> shoppingCart;
    ArrayList<Item> purchasedItems;


    public void show_member() {
        System.out.print("ID: " + this.id + " | Name: " + this.name + " | Email: " + this.email + " | Balance: " + this.balance + " ");
    }
    @Override
    public String toString() {
        return "ID: " + this.id + " | Name: " + this.name + " | Email: " + this.email + " | Balance: " + this.balance + " ";

    }

    public User(String id, String name, String password, String email, double balance, int type) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.type = type;
        shoppingCart = new ArrayList<Item>();
        purchasedItems = new ArrayList<Item>();
    }

    public User() {
        //empty
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public Object clone(){
        try{
            return super.clone();
        }catch(Exception e){
            return null;
        }
    }

}
