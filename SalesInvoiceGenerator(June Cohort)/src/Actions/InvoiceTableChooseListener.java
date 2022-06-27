
package Actions;

import DataEntry.InvoiceHeader;
import DataEntry.InvoiceLine;
import DataEntry.InvoiceLineTM;
import Interfaces.InvoiceApp;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class InvoiceTableChooseListener implements ListSelectionListener{

    private InvoiceApp App;
    
    public InvoiceTableChooseListener (InvoiceApp App){
    
    this.App = App;
       }
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {

        int ChosenInvoiceIndex = App.getTableOfInvoices().getSelectedRow();
        System.out.println("Invoice Chose: " + ChosenInvoiceIndex); 
        if (ChosenInvoiceIndex != -1){
        InvoiceHeader  ChosenInvoice = App.getInvARRAY().get(ChosenInvoiceIndex);
        ArrayList<InvoiceLine> L = ChosenInvoice.getLines();
        InvoiceLineTM LINETM = new InvoiceLineTM(L);
        App.setLINESARRAY(L);
        App.getTableOfInvoiceItems().setModel(LINETM);
        App.getClientNameLabel().setText(ChosenInvoice.getClient());
        App.getInvoiceNumLabel().setText(""+ChosenInvoice.getNumber());
        App.getInvoiceTotalLabel().setText(""+ChosenInvoice.getInvoiceTotal());
        App.getInvoiceDateLabel().setText(InvoiceApp.DF.format(ChosenInvoice.getInvoiceDate()));
        }
        
        
        
    }
    
    
    
}
