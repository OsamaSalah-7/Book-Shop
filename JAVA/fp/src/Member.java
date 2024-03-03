
public class Member extends User {

    private int login_count=0;

    @Override
    public void show_member() {
        System.out.print("ID: " + this.getId() + " | Name: " + this.getName() + " | Email: " + this.getEmail() + " | Balance: " + this.getBalance() + " ");
    }

    public Member(String id, String name, String password, String email, double balance, int type) {
        super (id, name, password, email, balance, type);
    }

    public Member() {
        // TODO Auto-generated constructor stub
    }

    public int getLogin_count() {
        return login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }

}
