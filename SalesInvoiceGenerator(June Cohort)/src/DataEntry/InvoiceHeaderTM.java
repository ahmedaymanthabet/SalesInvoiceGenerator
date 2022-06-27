
package DataEntry;

import Interfaces.InvoiceApp;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvoiceHeaderTM extends AbstractTableModel{

    private ArrayList<InvoiceHeader> InvARRAY;
    private String[] ColumnsNames = {"Number", "Date", "Client Name", "Inv Total"};

    public InvoiceHeaderTM(ArrayList<InvoiceHeader> InvARRAY) {
        this.InvARRAY = InvARRAY;
    }
    
    
    
    @Override
    public int getRowCount() {
        return InvARRAY.size(); 
    }

    @Override
    public int getColumnCount() {
        return ColumnsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader d = InvARRAY.get(rowIndex);
        switch (columnIndex){
            case 0:
                return d.getNumber();
            case 1:
                return InvoiceApp.DF.format(d.getInvoiceDate());
            case 2:            
                return d.getClient();
            case 3:
                return d.getInvoiceTotal(); 
        
        }
        return "";
    }
    
    public String getColumnName (int ColumnName){
        return ColumnsNames [ColumnName];
    
    }  
}
