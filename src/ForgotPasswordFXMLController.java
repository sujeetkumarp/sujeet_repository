/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ECUtils.GUIValidator;
import NewInterface0.FXMLConstants;
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
public class ForgotPasswordFXMLController implements Initializable, FXMLConstants {
    GUIValidator v1 = new GUIValidator();
    static Users u1 = null;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtQuestion;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Label lblQuestion;
    @FXML
    private Label lblAnswer;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label lblHidden;
    @FXML
    private Label lblChange;
    @FXML
    private Label lblBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblHidden.setVisible(false);
        lblChange.setVisible(false);
        v1.addRequiredValidator(txtUser);
        
    }    

    @FXML
    private void he(ActionEvent event) {
        if(event.getSource()==txtUser){
        u1 = UserDAO.findByName(txtUser.getText());
        if(u1 != null){
        txtQuestion.setText(u1.getSecurityQues());
        }
        else{
        JOptionPane.showMessageDialog(null, "The username you entered do not exist!");
        }
        }
        else if(event.getSource()==btnSubmit){
            u1 = UserDAO.findByName(txtUser.getText());
            if(txtAnswer.getText().equals(u1.getAnswer())){
            JOptionPane.showMessageDialog(null, "Congratulations!!");
           ChangePasswordFXMLController.userName=u1.getUserNAme();
            lblHidden.setVisible(true);
            lblChange.setVisible(true);
            }
        }
    }

    @FXML
    private void mc(MouseEvent event) throws IOException {
        if(event.getSource()==lblBack){
            Parent root = FXMLLoader.load(getClass().getResource(LOGIN_PAGE));
            Stage stage = (Stage) lblBack.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
            
            
            
    }
        else if(event.getSource()==lblChange){
        Parent root = FXMLLoader.load(getClass().getResource(CHANGE_PAGE));
            Stage stage = (Stage) lblBack.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        }
    
}
}
