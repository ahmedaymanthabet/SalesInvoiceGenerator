
package Actions;

import DataEntry.InvoiceHeader;
import DataEntry.InvoiceHeaderTM;
import DataEntry.InvoiceLine;
import DataEntry.InvoiceLineTM;
import Interfaces.InvHeaderD;
import Interfaces.InvLineD;
import Interfaces.InvoiceApp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ProjectActions implements ActionListener {

    
    private InvoiceApp App;
    private InvHeaderD HeaderD;
    private InvLineD LineD; 

    public ProjectActions (InvoiceApp App){
    this.App = App;
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
 
        switch (e.getActionCommand()){
            
            case "Load Files":
                 Load();
                break;
                
            case "Save Files":
                 Save();
                break;
                
            case "Create New Invoice":
                CreateInvoice();
                break;
                
            case "Delete Invoice":
                DeleteInvoice();
                break;
                
            case "New Line":
                CreateLine();
                break;
                
            case "Delete Line":
                DeleteLine();
                break;   
                
             case "NewInvCANCEL":    
                NewInvDialogCANCEL();
                break;
              
            case "NewInvOK":
                NewInvDialogOK();   
                break;
             
            case "NewLINECANCEL":
                NewLINEDialogCANCEL();
                break;
                
            case "NewLINEOK":
                NewLINEDialogOK();
                 break;
        }

    }
  private void Load() {
        
      JFileChooser FileSelector = new JFileChooser();
       try {
          int Outcome = FileSelector.showOpenDialog(App);
          if (Outcome == JFileChooser.APPROVE_OPTION) {
          
          File HeadTitle = FileSelector.getSelectedFile();
          Path HeadPath = Paths.get(HeadTitle.getAbsolutePath());
          List<String> HeadLines = Files.readAllLines(HeadPath);
          ArrayList<InvoiceHeader> InvoiceTitles = new ArrayList<>();
          for (String HeadLine : HeadLines){
              String[] x = HeadLine.split(",");
              String s1 = x[0];  // invoice number
              String s2 = x[1];  // invoice date
              String s3 = x[2]; // client name
              int a = Integer.parseInt(s1);
              Date InvD = InvoiceApp.DF.parse(s2);
              InvoiceHeader Header = new InvoiceHeader(a, s3, InvD);
              InvoiceTitles.add(Header);
          }
           App.setInvARRAY(InvoiceTitles); 
           Outcome = FileSelector.showOpenDialog(App);
           if (Outcome == JFileChooser.APPROVE_OPTION){
               File LineTitle = FileSelector.getSelectedFile();
               Path LinePath = Paths.get(LineTitle.getAbsolutePath());
               List<String> LineLINES = Files.readAllLines(LinePath);
               ArrayList <InvoiceLine> InvLines = new ArrayList<>();
               for (String LineLINE : LineLINES){
                String[] y = LineLINE.split(",");
                String s1 = y[0];   // invoice number
                String s2 = y[1];   // item name
                String s3 = y[2];   // price
                String s4 = y[3];   // count
                int b = Integer.parseInt(s1);
                double ItemPrice = Double.parseDouble(s3);
                int Counter = Integer.parseInt(s4);
                InvoiceHeader c = App.getInvoiceObj(b);
                InvoiceLine InvLINE = new InvoiceLine(s2, ItemPrice, Counter, c);
                c.getLines().add(InvLINE);
                
             
               }
           }
           InvoiceHeaderTM HeaderTM = new InvoiceHeaderTM(InvoiceTitles);
           App.setHeaderTM(HeaderTM); 
           App.getTableOfInvoices().setModel(HeaderTM);
              System.out.println("files reasd");
       }  
      

       
       }catch (IOException Exception){
               JOptionPane.showMessageDialog(App, Exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               
      
        } catch (ParseException Exception){
              JOptionPane.showMessageDialog(App, Exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
              
    }
   
  
  private void Save() {
      ArrayList<InvoiceHeader> InvARRAYS = App.getInvARRAY();
      JFileChooser Selector = new JFileChooser();
      try {
      int R = Selector.showSaveDialog(App);
      if (R == JFileChooser.APPROVE_OPTION){
          File HeaderF = Selector.getSelectedFile();
          FileWriter HeaderFileWriter = new FileWriter(HeaderF);
          String H = "";
          String Rows = "";
          for (InvoiceHeader Element : InvARRAYS){
              H += Element.toString();
              H += "\n";
              for (InvoiceLine Row : Element.getLines()){
                Rows += Row.toString();
                Rows += "\n";
              
              }
          }
          H = H.substring(0, H.length()-1);
          Rows = Rows.substring(0, Rows.length()-1);
          R = Selector.showSaveDialog(App);
          File LF = Selector.getSelectedFile();
          FileWriter LINEFileWrtier = new FileWriter(LF);
          HeaderFileWriter.write(H);
          LINEFileWrtier.write(Rows);
          HeaderFileWriter.close();
          LINEFileWrtier.close();
           
      }
      } catch (IOException Exception) {
         JOptionPane.showMessageDialog(App, Exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

      }
      
    }


    private void CreateInvoice() {
        HeaderD = new InvHeaderD(App);
        HeaderD.setVisible(true);
    }

    private void DeleteInvoice() {
        int ChosenInvIndex = App.getTableOfInvoices().getSelectedRow();
        if (ChosenInvIndex != -1){
            App.getInvARRAY().remove(ChosenInvIndex);
            App.getHeaderTM().fireTableDataChanged();
            App.getTableOfInvoiceItems().setModel(new InvoiceLineTM(null));
            App.setLINESARRAY(null);
            App.getClientNameLabel().setText("");
            App.getInvoiceNumLabel().setText("");
            App.getInvoiceTotalLabel().setText("");
            App.getInvoiceDateLabel().setText("");
 
        }
    }

    private void CreateLine() {
        LineD = new InvLineD(App);
        LineD.setVisible(true);
        
    }

    private void DeleteLine() {
        int ChosenLineIndex = App.getTableOfInvoiceItems().getSelectedRow();
        int ChosenInvIndex = App.getTableOfInvoices().getSelectedRow();
        if (ChosenLineIndex != -1){
            App.getLINESARRAY().remove(ChosenLineIndex);
            InvoiceLineTM LineTM = (InvoiceLineTM) App.getTableOfInvoiceItems().getModel();
            LineTM.fireTableDataChanged(); 
            App.getHeaderTM().fireTableDataChanged();
            App.getInvoiceTotalLabel().setText(""+App.getInvARRAY().get(ChosenInvIndex).getInvoiceTotal());
            App.getTableOfInvoices().setRowSelectionInterval(ChosenInvIndex, ChosenInvIndex);
            
        }        
    }

    private void NewInvDialogCANCEL() {
        
        HeaderD.setVisible(false);
        HeaderD.dispose();
        HeaderD = null;
    }

    private void NewInvDialogOK() {
        
        HeaderD.setVisible(false);
        
        String ClientName = HeaderD.getClientNameField().getText();
        String DATE = HeaderD.getInvoiceDate().getText();
        Date o = new Date();
        try {
        o = InvoiceApp.DF.parse(DATE);
        } catch (ParseException Exception){
        JOptionPane.showMessageDialog(App, "can't Parse DATE, restting to Today's date.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        } 
        
        int INVNUM = 0;
        for(InvoiceHeader INV : App.getInvARRAY()){
        if (INV.getNumber() > INVNUM) INVNUM = INV.getNumber();
        
        }
        INVNUM ++;
        InvoiceHeader E = new InvoiceHeader(INVNUM, ClientName, o);
        App.getInvARRAY().add(E);
        App.getHeaderTM().fireTableDataChanged();
        HeaderD.dispose();
        HeaderD = null;
    }

    private void NewLINEDialogCANCEL() {
        LineD.setVisible(false);
        LineD.dispose();
        LineD = null;
    }

    private void NewLINEDialogOK() {
        LineD.setVisible(false);
        
        String ItemsName = LineD.getItemsNameField().getText();
        String ItemsCounter = LineD.getItemsCounterField().getText();
        String ItemsPrice = LineD.getItemsPriceField().getText();
        int Counter = 1;
        double Cost = 1;
        try {
         Counter = Integer.parseInt(ItemsCounter);
       
        } catch (NumberFormatException Exception){
        
        JOptionPane.showMessageDialog(App, "can't convert num", "Invalid Format", JOptionPane.ERROR_MESSAGE);
        }
         try {
         Cost = Double.parseDouble(ItemsPrice);
       
        } catch (NumberFormatException Exception){
        
        JOptionPane.showMessageDialog(App, "can't convert num", "Invalid Format", JOptionPane.ERROR_MESSAGE);
        }
        int ChoseInvHeader = App.getTableOfInvoices().getSelectedRow();
        if (ChoseInvHeader != -1){
            InvoiceHeader InvH = App.getInvARRAY().get(ChoseInvHeader);
            InvoiceLine LineH = new InvoiceLine(ItemsName, Cost, Counter, InvH);
           // InvH.getLines().add(LineH);
            App.getLINESARRAY().add(LineH);
            InvoiceLineTM LineTM = (InvoiceLineTM) App.getTableOfInvoiceItems().getModel();
            LineTM.fireTableDataChanged(); 
            App.getHeaderTM().fireTableDataChanged();
        
        } 
        App.getTableOfInvoices().setRowSelectionInterval(ChoseInvHeader, ChoseInvHeader);
        LineD.dispose();
        LineD = null;
    }

   
    
}
