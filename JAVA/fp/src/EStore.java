import java.util.Scanner;

import javax.swing.JFrame;

import java.util.ArrayList;
public class EStore {

    static ArrayList<User> users;
    static ArrayList<Item> items;

    static User currentUser;


    public static void main (String[] args) {

        init();

        loginPage page1 = new loginPage(currentUser,users, items);
        page1.setVisible(true);
        page1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void init() {

        users = new ArrayList<User>();
        items = new ArrayList<Item>();


        User userTemp = new Admin ("2351", "Osama","00", "o@gmail.com", 150 , 1);
        users.add(userTemp);

        User userTemp1 = new Member ("4956", "Fadi","55", "f@gmail.com", 198 , 2);
        users.add(userTemp1);

        User userTemp2 = new Member ("3849", "Ahmad","44", "a@gmail.com", 950 , 2);
        users.add(userTemp2);

        User userTemp3 = new Member ("4782", "Jaber","33", "j@gmail.com", 20 , 2);
        users.add(userTemp3);

        User userTemp4 = new Member ("8384", "azam","22", "a@gmail.com", 360 , 2);
        users.add(userTemp4);

        User userTemp5 = new Member ("7839", "Ramiz","11", "r@gmail.com", 595 , 2);
        users.add(userTemp5);

        Item bookTemp = new Book(12, "2022", "I1", "A1", true, 45, 25, 26 );
        items.add(bookTemp);

        Item bookTemp1 = new Book(13, "2022", "I2", "A2", true, 75, 40, 22 );
        items.add(bookTemp1);

        Item bookTemp2 = new Book(14, "2022", "I3 ", "A3", false, 65, 0, 50 );
        items.add(bookTemp2);

    };

    public static int authenticate_user() {

        Scanner inputScan = new Scanner (System.in);

        System.out.print("Please enter the username: ");
        String userName = inputScan.nextLine();
        System.out.print("Please enter the password for " + userName + ": ");
        String pass = inputScan.nextLine();

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
            System.out.println("\nLogged in.");
            return tempIndex;
        }
        else
            System.out.println("\nNot found.");
        return -1;
    }

    public static void user_activities() {
        Scanner keyboard = new Scanner(System.in);


        System.out.print("\n\n\t Welcome " + currentUser.getName() + " To EStore "
                + "\n===============================");

        while (true) {

            System.out.print("\n1. Add item to shopping cart.  \n2. View shopping cart.  \n3. Remove item from shopping cart.  \n4. Checkout.  \n5. List all items.  \n6. Print previous purchases.\n \t");
            int choice = keyboard.nextInt();

            switch(choice) {

                case 1:
                    System.out.print("\n Please enter the item's ID. \n Enter 5 in the main menu to"
                            + " show all items. \n ID:  ");
                    int tempID1 = keyboard.nextInt();
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
                        System.out.println("\nItem not found.");
                        break;
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
                                System.out.print("\n Non members have a limit, can't exceed 300 NIS. "
                                        + "\n Would you like to become a member?  YES/NO:  ");
                                String choice2 = keyboard.next();

                                if (choice2.equalsIgnoreCase("yes")) {
                                    User tempMember = new Member();
                                    tempMember = (User) currentUser.clone();
                                    users.remove(currentUser);
                                    users.add(tempMember);
                                    currentUser = null;
                                    System.out.print("\n You have been changed from non member to "
                                            + "a full member in the Estore.");
                                    System.gc();
                                }
                                else {
                                    System.out.println("As you wish.");
                                    break;
                                }
                            }
                        }
                        currentUser.shoppingCart.add(items.get(tempID2));
                        items.get(tempID2).setNumberOfCopies(items.get(tempID2).getNumberOfCopies()-1);
                        System.out.println("\n Item is added.");

                    } else {
                        System.out.println("\n Items is out of stock.");
                        break;
                    }
                    break;

                case 2:
                    if (currentUser.shoppingCart.isEmpty())
                        System.out.print("\n Cart is empty.\n");
                    else {
                        double total = 0;
                        int tempIndex = 0;
                        System.out.print("\n \t\tCart contents: \t\t\n"
                                + "================================\n");
                        for (Item item1 : currentUser.shoppingCart) {
                            tempIndex++;
                            System.out.print(tempIndex + ". ");
                            item1.show_item();
                            System.out.println("");
                            total += item1.getPhysicalCost();
                        }
                        System.out.println("Total: " + total);

                        if (total > 1000) {
                            total = total * 0.92;
                            System.out.print("You get 8% for orders over 1000NIS. \n"
                                    + "Your total now is: " + total + " NIS. \n");
                        }
                        else if (total > 800) {
                            total = total * 0.95;
                            System.out.print("You get 5% for orders over 800NIS. \n"
                                    + "Your total now is: " + total + " NIS. \n");
                        }
                    }
                    break;
                case 3:
                    if (currentUser.shoppingCart.isEmpty()) {
                        System.out.print("\nCart is already empty. \n");
                        break;
                    }
                    System.out.print("Enter 2 in the main menu to view cart. "
                            + "\nPlease enter the item's index (in cart) to delete: ");
                    int choice1 = keyboard.nextInt();
                    currentUser.shoppingCart.remove(choice1-1);
                    System.out.print("\n Item has been deleted.");

                    break;
                case 4:
                    System.out.print("\n \t\t\tCHECKOUT\t\t\n "
                            + "======================");

                    for (Item item2 : currentUser.shoppingCart) {
                        currentUser.purchasedItems.add(item2);
                        currentUser.shoppingCart.remove(item2);
                    }
                    break;

                case 5:
                    int tempIndex = 0;
                    System.out.print("\n \t\tALL ITEMS IN ESTORE \t\t"
                            + "\n====================================\n");
                    for (Item item : items) {
                        tempIndex++;
                        System.out.print(tempIndex + ".  ");
                        item.show_item();
                        System.out.println("");
                    }
                    break;

                case 6:
                    int tempIndex1 = 0;
                    System.out.print("\n \t\tPREVIOUS PURCHASES \t\t"
                            + "\n====================================\n");
                    for (Item item : currentUser.purchasedItems) {
                        tempIndex1++;
                        System.out.print(tempIndex1);
                        item.show_item();
                        System.out.println("");
                    }
                    break;
            }
        }
    }


    public static void admin_activites() {

        if (currentUser.getType() != 1) {
            System.out.println("\nYou don't have access.");
            return;
        }

        Scanner keyboard = new Scanner(System.in);

        System.out.print("\n\n\t"
                + "  WELCOME ADMIN \n==========================================\n");

        while (true) {

            System.out.print("\n1. Show all items. \n2. Display all users. \n3. Change price of item. \n4. Add new user. \n5. Add copies to the items. \n6. Quit. \n\n");
            int choice = keyboard.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("\n\tALL ITEMS IN ESTORE\n "
                            + "====================================\n\n");

                    int tempIndex =0;
                    for (Item tt : items) {
                        tempIndex++;
                        System.out.println(tempIndex + ".  " +tt);}
                    System.out.println("");
                    break;
                case 2:

                    System.out.print("\n\tALL USERS IN ESTORE\n "
                            + "====================================\n\n");
                    for (User acc : users) {
                        acc.show_member(); System.out.print("  \n");
                    }
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Please enter the item's ID: ");
                    int tempID = keyboard.nextInt();

                    boolean found = false;

                    for (Item book : items) {
                        if (book.getItem_id() == tempID) {
                            Book book1 = (Book) book;

                            System.out.println("Please enter the new price");
                            double newPrice = keyboard.nextDouble();

                            book1.change_price(newPrice);

                            System.out.println("\nPRICE CHANGED.");
                            found = true;
                            break;
                        }
                    }

                    if(!found) System.out.println("ITEM IS NOT FOUND.");
                    break;

                case 4:

                    ArrayList<String> details = new ArrayList<String>();
                    System.out.print("Please enter ID then password then name then email ");

                    User newUser = null;

                    for (int i = 0 ; i<4 ; i++ ) {
                        details.add(keyboard.next());
                    }

                    boolean valid = false;

                    do {

                        System.out.print("\nType of new user: \n  1 Admin \n  2 Member\n");
                        int choice1 = keyboard.nextInt();

                        switch (choice1) {

                            case 1 :
                                newUser = new Admin(details.get(0), details.get(2), details.get(1), details.get(3), 0.0, 1);
                                valid = true;
                                break;

                            case 2 :
                                newUser = new Member(details.get(0), details.get(2), details.get(1), details.get(3), 0.0, 2);
                                valid = true;
                                break;
                            default: System.out.println("Invalid input.");
                                break;
                        }
                        break;} while (!valid);

                    users.add(newUser);
                    break;

                case 5:
                    System.out.print("Enter book ID: ");
                    int tempID2 = keyboard.nextInt();

                    boolean found2 = false;

                    for (Item book : items) {
                        if (book.getItem_id() == tempID2) {
                            Book book1 = (Book) book;
                            book1.add_copies();
                            found2 = true;
                            System.out.print("\nDONE.\n");
                            break;
                        }
                    }
                    if(!found2) System.out.print("\nITEM NOT FOUND\n");
                    break;

                case 6:
                    System.out.println("Exited.");
                    return;

                default:
                    System.out.println("Invalid entry.");
                    break;

            }
        }


    }
}



