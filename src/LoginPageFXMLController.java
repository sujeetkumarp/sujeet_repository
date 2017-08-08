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
import TextEditor.bean.Users;
import TextEditor.DAO.UserDAO;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sun.security.provider.MD5;

/**
 * FXML Controller class
 *
 * @author SAURABH SAHAB
 */
public class LoginPageFXMLController implements Initializable, FXMLConstants {

    GUIValidator v1 = new GUIValidator();
    static Users u1 = null;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPwd;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblForgot;
    @FXML
    private Label lblRegister;
    @FXML
    private Button btnReset;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v1.addRequiredValidator(txtPwd);
        v1.addRequiredValidator(txtUser);
        
    }    

    @FXML
    private void ae(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        if(event.getSource()==btnLogin){
        if(v1.validateAll()){
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(txtPwd.getText().getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < byteData.length;i++)
                sb.append(Integer.toString((byteData[i] & 0xff)+0x100, 16).substring(1));
            u1 = UserDAO.validate(txtUser.getText(), sb.toString());
            if(u1 != null){
                Parent root = FXMLLoader.load(getClass().getResource(TEXT_EDITOR));
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(0);
                stage.setY(0);
                stage.show();
                
            }
            else{
            JOptionPane.showMessageDialog(null, "Incorrect Information!!");
            }
        }
        }
        else if(event.getSource()==btnReset){
        Stage stage = (Stage) btnReset.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(LOGIN_PAGE));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        }
    }

    @FXML
    private void mc(MouseEvent event) throws IOException {
        if(event.getSource()==lblForgot){
        Stage stage = (Stage) lblForgot.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(FORGOT_PAGE));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        }
        else if(event.getSource()==lblRegister){
        Stage stage = (Stage) lblRegister.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(REGISTER_PAGE));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        }
    }
    
}
