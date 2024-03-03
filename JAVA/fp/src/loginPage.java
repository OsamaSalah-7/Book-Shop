import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class loginPage extends JFrame implements ActionListener {
    private JLabel passwordLabel, label;
    private JTextField user;
    private JPasswordField password;
    private JButton button;
    private JPanel panel;

    private User currentUser;
    private ArrayList<User> users;
    private ArrayList<Item> items;

    public loginPage(User enteredUser, ArrayList<User> usersList, ArrayList<Item> itemss) {

        super("Login");
        this.setLocationRelativeTo(null);
        currentUser = enteredUser;
        users = usersList;
        items = itemss;

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.RED);
        panel.setForeground(Color.white);
        this.add(panel);

        this.setSize(400, 200);

        label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 55, 70, 20);
        panel.add(passwordLabel);

        user = new JTextField();
        user.setBounds(100, 27, 250, 40);
        panel.add(user);


        password = new JPasswordField();
        password.setBounds(100, 75, 250, 40);
        panel.add(password);

        button = new JButton("Login");
        button.setBounds(100, 110, 90, 90);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        panel.add(button);
        button.addActionListener(this);



    }
    @Override
    public void actionPerformed(ActionEvent event) {

        String userName = user.getText();
        String pass = password.getText();

        boolean logged_in = false;
        int tempIndex =0;

        for (User acc : users) {
            if (acc.getName().equalsIgnoreCase(userName) && acc.getPassword().equals(pass)) {
                logged_in = true;
                break;}
            else tempIndex++;
        }

        if (logged_in) {

            currentUser = users.get(tempIndex);
            JOptionPane.showMessageDialog(null, "Login Successful");

            if (currentUser.getType() == 1) {
                AdminPage page2 = new AdminPage(users, items);
                page2.setVisible(true);
                page2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}

            else {
                MemberPage page3 = new MemberPage(currentUser, items, users);
                page3.setVisible(true);
                page3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
    }

}



