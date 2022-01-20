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
import javafx.scene.layout.AnchorPane;
import model.User;


public class FXMLWelcomeController implements Initializable {
    private FXMLPrincipalController principal;

    @FXML
    private Label fxWelcomeTitle;

    private String login;

    public void setLogin(User user) {
        this.login = user.getName();
        fxWelcomeTitle.setText("Welcome " + login);
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxWelcomeTitle.setText("Welcome back " + login);
    }    
    
}
