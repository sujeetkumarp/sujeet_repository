/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ECUtils.GUIValidator;
import NewInterface0.FXMLConstants;
import TextEditor.bean.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import TextEditor.DAO.UserDAO;
import TextEditor.bean.Users;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author SAURABH SAHAB
 */
public class ChangePasswordFXMLController implements Initializable, FXMLConstants {

    GUIValidator v1 = new GUIValidator();
    static Users u1 = null;
    @FXML
    private TextField txtPwd;
    @FXML
    private TextField txtPwd2;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label lblBack;
    public static String userName = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v1.addRequiredValidator(txtPwd);
        v1.addRequiredValidator(txtPwd2);
        v1.addPassValidator(txtPwd);
        
        
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource()==btnSubmit){
        u1 = UserDAO.findByName(userName);
        if(txtPwd.getText().equals(txtPwd2.getText())){
        u1.setPassword(txtPwd.getText());
        u1.setUserNAme(userName);
        UserDAO.update(u1);
            JOptionPane.showMessageDialog(null, "Password changed! Go back to login screen to log in.");
        }
        }
    }

    @FXML
    private void mc(MouseEvent event) throws IOException {
        if(event.getSource()==lblBack){
        Stage stage = (Stage) lblBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(LOGIN_PAGE));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        
        }
    }
    
}
