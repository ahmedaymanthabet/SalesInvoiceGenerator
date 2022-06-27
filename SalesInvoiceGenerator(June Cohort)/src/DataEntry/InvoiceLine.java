
package DataEntry;


public class InvoiceLine {
    
    int InvoiceNumber;
    private String Items;
    private double ItemPrice;
    private int ItemsCount;
    private InvoiceHeader header;

    

    public InvoiceLine() {
    }

    public InvoiceLine(String Items, double ItemPrice, int ItemsCount, InvoiceHeader header) {
        this.Items = Items;
        this.ItemPrice = ItemPrice;
        this.ItemsCount = ItemsCount;
        this.header = header;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getItems() {
        return Items;
    }

    public void setItems(String Items) {
        this.Items = Items;
    }

    public double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(double ItemPrice) {
        this.ItemPrice = ItemPrice;
    }

    public int getItemsCount() {
        return ItemsCount;
    }

    public void setItemsCount(int ItemsCount) {
        this.ItemsCount = ItemsCount;
    }

    
    public double getLinesTotal(){
        
        return ItemPrice * ItemsCount; 
    }

    @Override
    public String toString() {
        return header.getNumber() + ", " + Items + ", " + ItemPrice + ", " + ItemsCount;
    }

    
 
}
