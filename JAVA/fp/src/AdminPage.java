import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

public class AdminPage extends JFrame {

    JPanel panel1, panel2, panel3, panel4, panel5;
    JTextArea textArea1, textArea2;

    public AdminPage(ArrayList<User> users, ArrayList<Item> items) {

        super("Admin Page");
        JTabbedPane tabs = new JTabbedPane();
        add(tabs);
        setSize(600,650);

        //Just add box1 to tab1
        panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setForeground(Color.white);
        panel1.setLayout(new BorderLayout());

        textArea1 = new JTextArea("\t   \t\tALL ITEMS\n\n");
        textArea1.setSize(300, 600);
        Box box1 = Box.createHorizontalBox();
        box1.add(new JScrollPane(textArea1));
        box1.setBackground(Color.WHITE);
        panel1.add(box1);
        tabs.add("View all items", panel1);
        tabs.setBackground(Color.WHITE);
        //adding panel to tabs

        JButton button1 = new JButton("load");
        panel1.add(button1, BorderLayout.WEST);
        button1.addActionListener( new ActionListener() // anonymous inner class
                                   {
                                       // handle button event
                                       public void actionPerformed( ActionEvent event )
                                       {
                                           for (Item tt : items) {
                                               textArea1.insert(" ", 0);
                                               textArea1.append(tt.toString());
                                               textArea1.append("  \n");
                                           }
                                       }}
        );








        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(Color.BLUE);
        panel2.setForeground(Color.white);
        textArea2 = new JTextArea("\t   \t\tALL USERS\n\n");
        textArea2.setSize(300, 600);
        Box box2 = Box.createVerticalBox();
        box2.setBackground(Color.BLUE);
        box2.add(new JScrollPane(textArea2));
        panel2.add(box2);
        tabs.add("Display Users", panel2);
        JButton button2 = new JButton("Load");
        panel2.add(button2, BorderLayout.WEST);
        button2.addActionListener( new ActionListener() // anonymous inner class
                                   {
                                       // handle button event
                                       public void actionPerformed( ActionEvent event )
                                       {
                                           for (User uu : users) {
                                               textArea2.insert(" ", 0);
                                               textArea2.append(uu.toString());
                                               textArea2.append("  \n");
                                           }
                                       }}
        );

        panel3 = new JPanel();
        panel3.setBackground(Color.BLUE);
        panel3.setForeground(Color.white);
        tabs.add("Change price", panel3);
        tabs.setBackground(Color.WHITE);

        //panel3.setLayout(new BorderLayout());
        panel3.setLayout(null);
        JTextField field1 = new JTextField ("Enter the item's ID");
        field1.setBounds(100, 20, 110, 22);
        panel3.add(field1);
        JButton button3 = new JButton("Enter");
        button3.setBounds(100, 60, 110, 25);
        panel3.add(button3, BorderLayout.WEST);
        button3.addActionListener( new ActionListener() // anonymous inner class
        {
            // handle button event
            public void actionPerformed( ActionEvent event )
            {
                int tempID = Integer.parseInt(field1.getText());
                boolean found = false;

                for (Item book : items) {
                    if (book.getItem_id() == tempID) {
                        Book book1 = (Book) book;

                        double newPrice =Double.parseDouble(JOptionPane.showInputDialog(AdminPage.this,"Please enter the new price"));

                        book1.change_price(newPrice);

                        JOptionPane.showMessageDialog(AdminPage.this,"PRICE CHANGED");
                        found = true;
                    }
                }

                if(!found) JOptionPane.showMessageDialog(AdminPage.this,"ITEM NOT FOUND");
            }
        });

        panel4= new JPanel();
        panel4.setLayout(null);
        panel4.setBackground(Color.BLUE);
        panel4.setForeground(Color.white);
        tabs.add("Add user",panel4);
        tabs.setBackground(Color.white);


        JLabel label4$1 = new JLabel("ID");
        label4$1.setBounds(100, 7, 70, 20);
        panel4.add(label4$1);

        JTextField field4$1 = new JTextField();
        field4$1.setBounds(100, 27, 193, 28);
        panel4.add(field4$1);


        JLabel label4$2 = new JLabel("Password");
        label4$2.setBounds(100, 55, 70, 20);
        panel4.add(label4$2);


        JTextField field4$2 = new JTextField();
        field4$2.setBounds(100, 75, 193, 28);
        panel4.add(field4$2);


        JLabel label4$3 = new JLabel("Name");
        label4$3.setBounds(100, 103, 70, 20);
        panel4.add(label4$3);

        JTextField field4$3 = new JTextField();
        field4$3.setBounds(100, 123, 193, 28);
        panel4.add(field4$3);

        JLabel label4$4 = new JLabel("Email");
        label4$4.setBounds(100, 151, 70, 20);
        panel4.add(label4$4);

        JTextField field4$4 = new JTextField();
        field4$4.setBounds(100, 171, 193, 28);
        panel4.add(field4$4);


        String[] userTypeList = {"Admin", "Member"};
        JComboBox userTypeJList = new JComboBox(userTypeList);
        userTypeJList.setBounds(100, 215, 193, 33);
        panel4.add(userTypeJList);
        int[] choice1 = {0};
        userTypeJList.addItemListener(
                // anonymous inner class to handle JComboBox events
                new ItemListener() {
                    // handle JComboBox event
                    public void itemStateChanged( ItemEvent event )
                    {
                        // determine whether check box selected
                        if ( event.getStateChange() == ItemEvent.SELECTED )
                            choice1[0] = userTypeJList.getSelectedIndex();
                    }
                } // end anonymous inner class
        );


        JButton button4 = new JButton("Create new user");
        button4.setBounds(100, 275, 193, 33);
        button4.setForeground(Color.WHITE);
        button4.setBackground(Color.BLACK);
        panel4.add(button4);

        User[] newUser = new User[1];
        button4.addActionListener(new ActionListener() // anonymous inner class
                                  {
                                      // handle button event
                                      public void actionPerformed( ActionEvent event )
                                      {
                                          switch (choice1[0]) {

                                              case 0 :
                                                  newUser[0] = new Admin(field4$1.getText(), field4$3.getText(), field4$2.getText(), field4$4.getText(), 0.0, 1);
                                                  break;

                                              case 1 :
                                                  newUser[0] = new Member(field4$1.getText(), field4$3.getText(), field4$2.getText(), field4$4.getText(), 0.0, 2);
                                                  break;
                                              default: break;
                                          }
                                          users.add(newUser[0]);
                                          JOptionPane.showMessageDialog(AdminPage.this,"USER ADDED");
                                      }}
        );



        panel5 = new JPanel();
        tabs.add("Add copies", panel5);
        panel5.setLayout(null);
        panel5.setBackground(Color.BLUE);
        panel5.setForeground(Color.white);
        JTextField field5 = new JTextField ("Enter the item's ID");
        field5.setBounds(100, 20, 110, 22);
        panel5.add(field5);
        JButton button5 = new JButton("Load");
        button5.setBounds(100, 60, 110, 25);
        panel5.add(button5, BorderLayout.WEST);
        button5.setBackground(Color.gray);
        button5.addActionListener( new ActionListener() // anonymous inner class
        {
            // handle button event
            public void actionPerformed( ActionEvent event )
            {
                int tempID = Integer.parseInt(field5.getText());
                boolean found = false;

                for (Item book : items) {
                    if (book.getItem_id() == tempID) {
                        Book book1 = (Book) book;

                        book1.add_copies();

                        JOptionPane.showMessageDialog(AdminPage.this,"1 COPY ADDED");
                        found = true;
                    }
                }

                if(!found) JOptionPane.showMessageDialog(AdminPage.this,"ITEM NOT FOUND");
            }
        });



    }}


