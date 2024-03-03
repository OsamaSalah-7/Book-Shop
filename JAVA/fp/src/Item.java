
public class Item {
    private int item_id;
    private String add_date;
    private String title;
    private String distributer;
    private boolean digitalCopy;
    private double physicalCost;
    private double digitalCost;
    private int numberOfCopies;

    public Item(int item_id, String add_date, String title, String distributer, boolean digitalCopy
            , double physicalCost, double digitalCost, int numberOfCopies) {
        this.item_id = item_id;
        this.add_date=add_date;
        this.title=title;
        this.distributer=distributer;
        this.digitalCopy=digitalCopy;
        this.physicalCost=physicalCost;
        this.digitalCost=digitalCost;
        this.numberOfCopies=numberOfCopies;
    }


    public void show_item() {

    }
    public int getItem_id() {
        return item_id;
    }
    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    public String getAdd_date() {
        return add_date;
    }
    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDistributer() {
        return distributer;
    }
    public void setDistributer(String distributer) {
        this.distributer = distributer;
    }
    public boolean isDigitalCopy() {
        return digitalCopy;
    }
    public void setDigitalCopy(boolean digitalCopy) {
        this.digitalCopy = digitalCopy;
    }
    public double getPhysicalCost() {
        return physicalCost;
    }
    public void setPhysicalCost(double physicalCost) {
        this.physicalCost = physicalCost;
    }
    public double getDigitalCost() {
        return digitalCost;
    }
    public void setDigitalCost(double digitalCost) {
        this.digitalCost = digitalCost;
    }
    public int getNumberOfCopies() {
        return numberOfCopies;
    }
    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}

