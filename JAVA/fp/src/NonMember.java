
public class NonMember extends User {

    private int login_count=0;

    @Override
    public void show_member() {
        System.out.print("ID: " + this.getId() + " | Name: " + this.getName() + " | Email: " + this.getEmail() + " | Balance: " + this.getBalance() + " ");
    }

    public NonMember(String id, String password, String name, String email, double balance, int type) {
        super (id, name, password, email, balance, type);
    }

    public int getLogin_count() {
        return login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }
}
