/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLLoginController implements Initializable {

    private FXMLPrincipalController principal;
    @FXML
    private TextField fxUser;
    @FXML
    private TextField passBox;
    @FXML
    private Label errorBox;


    public void clickLogin() {
        principal.getServicesCustomers().
        if () {
            principal.setUsername(fxUser.getText());
            principal.setAdmin(true);
            principal.chargeWelcome();
        } else {
            errorBox.setText("User or password is wrong");
        }
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
