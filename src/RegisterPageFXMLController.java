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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import TextEditor.DAO.UserDAO;
import TextEditor.bean.Users;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class RegisterPageFXMLController implements Initializable, FXMLConstants {

    GUIValidator v1 = new GUIValidator();
    static Users u1 = null;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField PFPwd;
    @FXML
    private PasswordField PFPwd2;
    @FXML
    private ComboBox<String> cmb;
    @FXML
    private TextField txtAnswer;
    @FXML
    private Button btnSubmit;
    @FXML
    private Label lblBack;
    @FXML
    private Label lblMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        v1.addRequiredValidator(txtUser);
        v1.addRequiredValidator(txtAnswer);
        v1.addRequiredValidator(PFPwd);
        v1.addRequiredValidator(PFPwd2);
        v1.addPassValidator(PFPwd);
        v1.addRequiredValidator(cmb);
        cmb.getItems().addAll(
                "What is the city you were born in ?",
                "Who is the prime minister ?",
                "Who is the president ?",
                "What is the name of your high school ?"
        );

    }

    @FXML
    private void kr(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
            if (PFPwd.getText().equals(PFPwd2.getText())) {
                lblMsg.setText("Password Match!!");
                lblMsg.setTextFill(Color.rgb(21, 117, 84));
            } else {
                lblMsg.setText("Password do not match!!");
                lblMsg.setTextFill(Color.rgb(210, 39, 30));
            }
        }
    }

    @FXML
    private void he(ActionEvent event) throws NoSuchAlgorithmException {
        if (event.getSource() == btnSubmit) {
            if (PFPwd.getText().equals(PFPwd2.getText())) {
                if (v1.validateAll()) {
                    if (UserDAO.findByName(txtUser.getText()) != null) {
                        JOptionPane.showMessageDialog(null, "The user already exists.");
                        return;
                    } else {
                        Users p1 = new Users();
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(PFPwd.getText().getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < byteData.length;i++)
                sb.append(Integer.toString((byteData[i] & 0xff)+0x100, 16).substring(1));
                        p1.setUserNAme(txtUser.getText());
                        p1.setAnswer(txtAnswer.getText());
                        p1.setPassword(sb.toString());
                        p1.setSecurityQues(cmb.getValue().toString());
                        UserDAO.insert(p1);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Passwords don't match!!");

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
        stage.show();
        }
    }

}
