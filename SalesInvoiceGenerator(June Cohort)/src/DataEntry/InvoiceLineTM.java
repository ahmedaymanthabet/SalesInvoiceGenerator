
package DataEntry;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvoiceLineTM extends AbstractTableModel {

    private ArrayList<InvoiceLine> LINESARRAY;
    private String[] ColumnsNames = {"Name", "Item Price", "Number OF Items", "Lines Total"};

    public InvoiceLineTM(ArrayList<InvoiceLine> LINESARRAY) {
        this.LINESARRAY = LINESARRAY;
        
    }
    
    @Override
    public int getRowCount() {
        return LINESARRAY == null ? 0 : LINESARRAY.size();
        
    }

    @Override
    public int getColumnCount() {
        return ColumnsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (LINESARRAY == null)
        {return "";}
        else {
        InvoiceLine InvoiceL = LINESARRAY.get(rowIndex);
        switch (columnIndex){
            case 0: 
                return InvoiceL.getItems();
            case 1:
                return InvoiceL.getItemPrice();
            case 2:
                return InvoiceL.getItemsCount();
            case 3:
                return InvoiceL.getLinesTotal();
            default: 
                    return "";
        }}
        
       
    }
        

    @Override
    public String getColumnName(int ColumnName) {
        return ColumnsNames[ColumnName];

        
    }
    
    
}
