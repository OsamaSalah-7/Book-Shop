
public class Admin extends User{

    public Admin(String id, String password, String name, String email, double balance, int type) {
        super (id, password, name, email, balance, type);
    }

    @Override
    public void show_member() {
        System.out.print("ID: " + this.getId() + " | Name: " + this.getName() + " | Email: " + this.getEmail() + " | Balance: " + this.getBalance() + " ");
    }


}
