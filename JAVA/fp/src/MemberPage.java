import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

public class MemberPage extends JFrame {



    JPanel panel1, panel2, panel3, panel4, panel5;
    JTextArea textArea1, textArea2;

    public MemberPage(User currentUser, ArrayList<Item> items, ArrayList<User> users) {
        super("Member page");
        JTabbedPane tabs = new JTabbedPane();
        add(tabs);
        setSize(600,650);

        panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        tabs.add("Purchase item", panel1);
        panel1.setLayout(null);
        JTextField field1 = new JTextField ("Enter the item's ID");
        field1.setBounds(100, 20, 110, 22);
        panel1.add(field1);
        JButton button1 = new JButton("Enter");
        button1.setBounds(100, 60, 110, 25);
        panel1.add(button1, BorderLayout.NORTH);
        button1.addActionListener( new ActionListener() // anonymous inner class
        {
            // handle button event
            public void actionPerformed( ActionEvent event )
            {
                int tempID1 = Integer.parseInt(field1.getText());
                int tempID2=0;
                boolean found = false;
                for(Item tt : items) {
                    if(tt.getItem_id()== tempID1) {
                        found = true;
                        break;
                    }
                    else
                        tempID2++;
                }

                if (!found) {
                    JOptionPane.showMessageDialog(MemberPage.this,"ITEM NOT FOUND");
                }

                if (items.get(tempID2).getNumberOfCopies() > 0) {
                    double amount = 0;
                    if (currentUser.shoppingCart.size() == 0)
                        amount = (items.get(tempID2).getPhysicalCost());
                    else {
                        for (Item itemTempp : currentUser.shoppingCart) {
                            amount += itemTempp.getPhysicalCost();
                        }
                    }
                    if (amount > 300) {
                        if (currentUser.getType() == 3) {
                            String choice2 = JOptionPane.showInputDialog(MemberPage.this,"Non members have a limit, can't exceed 300 NIS. \nWould you like to become a member?  YES/NO:  ");


                            if (choice2.equalsIgnoreCase("yes")) {
                                User tempMember = new Member();
                                tempMember = (User) currentUser.clone();
                                users.remove(currentUser);
                                users.add(tempMember);
                                JOptionPane.showMessageDialog(MemberPage.this,"Your account has been changed from non member to full member in our ESTORE.");

                            }
                            else {
                                JOptionPane.showMessageDialog(MemberPage.this,"AS YOU WISH");
                            }
                        }
                    }
                    currentUser.shoppingCart.add(items.get(tempID2));
                    items.get(tempID2).setNumberOfCopies(items.get(tempID2).getNumberOfCopies()-1);
                    JOptionPane.showMessageDialog(MemberPage.this,"ITEM ADDED");

                } else {
                    JOptionPane.showMessageDialog(MemberPage.this,"ITEM OUT OF STOCK");
                }
            }
        });


        panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        panel2.setLayout(new BorderLayout());
        textArea2 = new JTextArea("\t   \t\tALL ITEMS\n\n");
        textArea2.setSize(300, 600);
        Box box2 = Box.createHorizontalBox();
        box2.add(new JScrollPane(textArea2));
        panel2.add(box2);
        tabs.add("Cart", panel2);  //adding panel to tabs

        JButton button2 = new JButton("Refresh");
        panel2.add(button2, BorderLayout.NORTH);
        button2.addActionListener( new ActionListener() // anonymous inner class
                                   {
                                       // handle button event
                                       public void actionPerformed( ActionEvent event )
                                       {
                                           if (currentUser.shoppingCart.isEmpty())
                                               JOptionPane.showMessageDialog(MemberPage.this,"CART IS EMPTY");
                                           else {
                                               double total = 0;
                                               int tempIndex = 0;
                                               for (Item item1 : currentUser.shoppingCart) {
                                                   tempIndex++;
                                                   textArea2.append(tempIndex + ".  ");
                                                   textArea2.append(item1.getTitle());
                                                   textArea2.append("  \n");
                                                   total += item1.getPhysicalCost();
                                               }

                                               textArea2.append("Total: "+ total + " NIS.\n" );

                                               if (total > 1000) {
                                                   total = total * 0.92;
                                                   textArea2.append("You get 8% for orders over 1000NIS. \n"
                                                           + "Your total now is: " + total + " NIS. \n");

                                               }
                                               else if (total > 800) {
                                                   total = total * 0.95;
                                                   textArea2.append("You get 5% for orders over 800NIS. \n"
                                                           + "Your total now is: " + total + " NIS. \n");
                                               }
                                           }
                                       }}
        );

        panel3 = new JPanel();
        tabs.add("Delete item", panel3);
        //panel3.setLayout(new BorderLayout());
        panel3.setLayout(null);
        JTextField field3 = new JTextField ("Enter the item's ID");
        field3.setBounds(100, 20, 110, 22);
        panel3.add(field3);
        JButton button3 = new JButton("Delete");
        button3.setBounds(100, 60, 110, 25);
        panel3.add(button3, BorderLayout.NORTH);
        button3.addActionListener( new ActionListener() // anonymous inner class
        {
            // handle button event
            public void actionPerformed( ActionEvent event )
            {
                if(currentUser.shoppingCart.isEmpty())
                    JOptionPane.showMessageDialog(MemberPage.this,"CART IS EMPTY");
                else {
                    int choice = Integer.parseInt(field3.getText());
                    currentUser.shoppingCart.remove(choice-1);
                    JOptionPane.showMessageDialog(MemberPage.this,"ITEM DELETED");
                }

            }
        });

        panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        JTextArea textArea4 = new JTextArea("\t   \t\tALL ITEMS\n\n");
        textArea4.setSize(300, 600);
        Box box4 = Box.createHorizontalBox();
        box4.add(new JScrollPane(textArea4));
        panel4.add(box4);
        tabs.add("View all items", panel4);  //adding panel to tabs

        JButton button4 = new JButton("Refresh");
        panel4.add(button4, BorderLayout.SOUTH);
        button4.addActionListener( new ActionListener() // anonymous inner class
                                   {
                                       // handle button event
                                       public void actionPerformed( ActionEvent event )
                                       {
                                           for (Item tt : items) {
                                               textArea4.insert(" ", 0);
                                               textArea4.append(tt.toString());
                                               textArea4.append("  \n");
                                           }
                                       }}
        );



    }
}
