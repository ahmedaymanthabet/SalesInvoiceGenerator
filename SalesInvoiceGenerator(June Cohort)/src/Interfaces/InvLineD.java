
package Interfaces;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvLineD extends JDialog {
    
    private JTextField ItemsNameField;
    private JTextField ItemsCounterField;
    private JTextField ItemsPriceField;
    private JLabel ItemsNameLabel;
    private JLabel ItemsCounterLabel;
    private JLabel ItemsPriceLabel;
    private JButton OKButton;
    private JButton CANCELButton;
    
    public InvLineD (InvoiceApp App){
        
    setSize(new Dimension(600, 400));
    ItemsNameLabel = new JLabel("Items Name");
    ItemsNameField = new JTextField(20);
    ItemsCounterLabel = new JLabel("Items Counter");
    ItemsCounterField = new JTextField(20);
    ItemsPriceLabel = new JLabel("Items Price");
    ItemsPriceField = new JTextField(20);
    OKButton = new JButton("OK");
    CANCELButton = new JButton("CANCEL");
    OKButton.setActionCommand("NewLINEOK");
    CANCELButton.setActionCommand("NewLINECANCEL");
    
    OKButton.addActionListener(App.getActionsListener());
    CANCELButton.addActionListener(App.getActionsListener());
    setLayout (new GridLayout (4, 2));
    
    add(ItemsNameLabel);
    add(ItemsNameField);
    add(ItemsCounterLabel);
    add(ItemsCounterField);
    add(ItemsPriceLabel);
    add(ItemsPriceField);
    add(OKButton);
    add(CANCELButton);
    
    
 
        
    }

    public JTextField getItemsNameField() {
        return ItemsNameField;
    }

    public void setItemsNameField(JTextField ItemsNameField) {
        this.ItemsNameField = ItemsNameField;
    }

    public JTextField getItemsCounterField() {
        return ItemsCounterField;
    }

    public void setItemsCounterField(JTextField ItemsCounterField) {
        this.ItemsCounterField = ItemsCounterField;
    }

    public JTextField getItemsPriceField() {
        return ItemsPriceField;
    }

    public void setItemsPriceField(JTextField ItemsPriceField) {
        this.ItemsPriceField = ItemsPriceField;
    }
    
}
