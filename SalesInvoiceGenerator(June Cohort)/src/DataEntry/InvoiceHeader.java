
package DataEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader {
    private int number;
    private String client;
    private Date InvoiceDate; 
    private ArrayList<InvoiceLine> lines;
    private DateFormat D = new SimpleDateFormat("dd-MM-yyyy");
    public InvoiceHeader() {
    }

    public InvoiceHeader(int number, String client, Date InvoiceDate) {
        this.number = number;
        this.client = client;
        this.InvoiceDate = InvoiceDate;
    }

    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceTotal(){
        
      double  Total = 0.0;
      
      for (int i = 0; i < getLines().size(); i++){
          
          Total += getLines().get(i).getLinesTotal();
      }
      
      return Total;  
    }

    @Override
    public String toString() {
        return  number + ", " + D.format(InvoiceDate) + ", " + client ;
    }
    
    
}
