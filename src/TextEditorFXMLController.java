/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import NewInterface0.FXMLConstants;
import javafx.scene.text.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SAURABH SAHAB
 */
public class TextEditorFXMLController implements Initializable, FXMLConstants {

    //The variables used in the following class are declared here.
    @FXML
    private MenuItem fileMenu;
    @FXML
    private MenuItem fileOpen;
    @FXML
    private MenuItem fileSave;
    @FXML
    private MenuItem fileSaveAs;
    @FXML
    private MenuItem filePrint;
    @FXML
    private MenuItem fileExit;
    @FXML
    private MenuItem editCut;
    @FXML
    private MenuItem editCopy;
    @FXML
    private MenuItem editPaste;
    @FXML
    private MenuItem editDelete;
    @FXML
    private MenuItem helpAbout;
    @FXML
    private ComboBox<String> cmbSize;
    @FXML
    private ComboBox<String> cmbFont;
    @FXML
    private TextArea txtArea;
    @FXML
    private Label lblposn;
    

    final java.awt.datatransfer.Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        txtArea.setText("");
        txtArea.setFont(Font.font("Times New Roman"));
        cmbFont.getItems().addAll("Times New Roman",
                "Arial Black",
                "Bradley Hand Itc",
                "Comic Sans"
        );
        cmbSize.getItems().addAll("2",
                "4", "8", "10", "12", "14", "18", "20");
    }
    //he(ActionEvent event) throws IOException{} is the default action event handler of the text editor fxml UI. 
    //the text editor UI is a text area with menubar at the top, it can be accessed only via login page.
    //there are three menu in menubar which in turn have several menuItems 
    @FXML
    private void he(ActionEvent event) throws IOException {
        //Codes for menu item exit
        if (event.getSource() == fileExit) {
            System.exit(0);
        // Codes for save menu item.
        } else if (event.getSource() == fileSave) {
            JFileChooser chooser = new JFileChooser(); //Using swing control in fx is bad idea, will replace it in future
            int chooserValue = chooser.showSaveDialog(null);
            if (chooserValue == JFileChooser.APPROVE_OPTION) {
                try {
                    PrintWriter fout = new PrintWriter(chooser.getSelectedFile()); // printwriter and scanner are the objects which thow the IOException.
                    fout.print(txtArea.getText());
                    fout.close();
                } catch (FileNotFoundException e) {
                    Logger.getLogger(TextEditorFXMLController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        //Codes for open menu item.
        else if (event.getSource() == fileOpen) {
            JFileChooser chooser = new JFileChooser(); // will change after learning fx more.
            int chooserValue = chooser.showSaveDialog(null);
            if (chooserValue == JFileChooser.APPROVE_OPTION) {
                try {
                    Scanner fin = new Scanner(chooser.getSelectedFile()); // the method is similar to save, only difference is the use of scanner in open and printwriter in save
                    String buffer = "";
                    while (fin.hasNext()) {
                        buffer += fin.nextLine() + "\n";
                    }
                    txtArea.setText(buffer);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "File not found!!");
                }
            }
        }
        //Codes for new menu item, mistakenly named fileMenu. Is subject to future change.
        else if (event.getSource() == fileMenu) {
            txtArea.setText("");
        }
        //Codes for print item. the function called is defined below every event handler.
        else if (event.getSource() == filePrint) {
            print(txtArea);
        }
        // Codes for cut menu item, taken straight from text input control class of java-8.
        else if (event.getSource() == editCut) {
            final String selected = txtArea.getSelectedText();
            if (selected.length() > 0) {
                final ClipboardContent content = new ClipboardContent();
                content.putString(selected);// what happens here is that we declare a clipboard content object and put the selected string of text area here and then 
                Clipboard.getSystemClipboard().setContent(content);// replace the selected text by an empty string thus, simulating cut action
            }
            txtArea.replaceSelection("");
        }
        //Codes for copy menu item. same as cut, but the selected text is not replaced by empty string
        else if (event.getSource() == editCopy) {
            final String selected = txtArea.getSelectedText();
            if (selected.length() > 0) {
                final ClipboardContent content = new ClipboardContent();
                content.putString(selected);
                Clipboard.getSystemClipboard().setContent(content);
            }
        }
        //Codes for paste menu item. Reverse of copy, the clipboard is checked for any available string
        // if any text is selected before executing paste, it gets replaced by the clipbooard string content.
        // otherwise the clipboard content is put before the cursor
        else if (event.getSource() == editPaste) {
            final Clipboard cb = Clipboard.getSystemClipboard();
            if(cb.hasString()){
                final String text = cb.getString();
                txtArea.replaceSelection(text);
                
            }
        } 
        // Codes for delete menu item. Replaces Selected string with empty string simulating deletion.
        else if (event.getSource() == editDelete) {

            txtArea.replaceSelection("");
        }
        // a personal PJ(Pakau joke for jargon enthusiasts)
        else if (event.getSource() == helpAbout) {
            int ch = JOptionPane.showConfirmDialog(null, "Do you really need our help?");
            if (ch == 0) {
                JOptionPane.showMessageDialog(null, "No, you don't. Legends make their own way!!");
            } else {
                JOptionPane.showMessageDialog(null, "Good choice!!");
            }

        }
        //Codes for font menu, it has a combo box which has been initialized to hold names of several fonts.
        // Selecting one replaces the current font with the font selected
        else if (event.getSource() == cmbFont) {
            txtArea.setFont(Font.font(cmbFont.getSelectionModel().getSelectedItem()));

        }
        //Codes for size menu. same working princliple as font menu, only differnce is the contents of combo box is now sizes
        else if (event.getSource() == cmbSize) {
            Font d = txtArea.getFont();
            Font n = new Font(d.getName(), Double.parseDouble(cmbSize.getSelectionModel().getSelectedItem()));
            txtArea.setFont(n);

        } 

    }

    //Keyrelease event for displaying the caret position, simulating status bar of ms word or wordpad to an underwhelming extent.
    @FXML
    private void kr(KeyEvent event) {
        int caretPosn = txtArea.getCaretPosition();
        lblposn.setText("" + caretPosn);
    }

    // The method that prints... 
    private void print(Node node) {
        System.out.println("Creating a printer job...");

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            System.out.println(job.jobStatusProperty().asString());

            boolean printed = job.printPage(node);
            if (printed) {
                job.endJob();
            } else {
                System.out.println("Printing failed.");
            }
        } else {
            System.out.println("Could not create a printer job.");
        }
    }
    // I hope to add only a few more things here, first are the accelerators for menu items(aka shortcut keys), second some prompt messages making the editor a bit more interactive in the process.

}
