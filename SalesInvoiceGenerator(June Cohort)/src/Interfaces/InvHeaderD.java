
package Interfaces;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvHeaderD extends JDialog {
    
    private JTextField ClientNameField;
    private JTextField InvoiceDate;
    private JLabel ClientNameLabel;
    private JLabel InvoiceDateLabel;
    private JButton OKButton;
    private JButton CANCELButton;
    
    
    
    public InvHeaderD (InvoiceApp App) {
        
    setSize(new Dimension(600, 400));
    ClientNameLabel = new JLabel("Client Name");
    ClientNameField = new JTextField(20);
    InvoiceDateLabel = new JLabel("Invoice DATE");
    InvoiceDate = new JTextField(20);
    OKButton = new JButton("OK");
    CANCELButton = new JButton("CANCEL");
    
    OKButton.setActionCommand("NewInvOK");
    CANCELButton.setActionCommand("NewInvCANCEL");
    
    OKButton.addActionListener(App.getActionsListener());
    CANCELButton.addActionListener(App.getActionsListener());
    setLayout (new GridLayout (3, 2));
    
    add(ClientNameLabel);
    add(ClientNameField);
    add(InvoiceDateLabel);
    add(InvoiceDate);
    add(OKButton);
    add(CANCELButton);
    
    
    
    }

    public JTextField getClientNameField() {
        return ClientNameField;
    }

    public void setClientNameField(JTextField ClientNameField) {
        this.ClientNameField = ClientNameField;
    }

    public JTextField getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(JTextField InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    
}
