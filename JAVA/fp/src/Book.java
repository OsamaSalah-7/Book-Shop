
public class Book extends Item{


    public Book (int item_id, String add_date, String title, String author,  boolean ebook, double b_cost, double e_cost, int number_of_copies) {
        super(item_id, add_date, title, author, ebook, b_cost, e_cost, number_of_copies);
    }

    public void show_item() {
        System.out.print(this.getTitle() + " by " + this.getDistributer() + "  |  "
                + "PRICE: " + this.getPhysicalCost());
    }
    private int get_number_of_copies() {
        return this.getNumberOfCopies();
    }
    public String ebook_availble() {
        if (this.isDigitalCopy())
            return "yes";
        else
            return "no";
    }
    public void add_copies() {
        int x = this.get_number_of_copies();
        this.setNumberOfCopies(x++);
    }
    public void change_price(double newPrice) {
        this.setPhysicalCost(newPrice);
    }

    @Override
    public String toString() {
        return this.getItem_id() + "  |   " + this.getTitle() + " by " + this.getDistributer() + " | Ebook availble: " + this.ebook_availble() + " | Number of copies availble: "
                + this.get_number_of_copies() + " | Price: " + this.getPhysicalCost() + "  ";
    }
}


