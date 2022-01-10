/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import services.impl.hibernate.ServicesCustomersHibernateImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLLoginController implements Initializable {

    private FXMLPrincipalController principal;
    @FXML
    private TextField fxUser;
    @FXML
    private TextField passBox;
    @FXML
    private Label errorBox;


    public void clickLogin() {
        User user = ((ServicesCustomersHibernateImpl) principal.getServicesCustomers()).login(
                User.builder()
                        .id(-1)
                        .name(fxUser.getText())
                        .password(passBox.getText())
                        .build()
        );
        if (user.getId() != -1) {
            int isAdmin = user.getCustomer() == null ? 0 : 1;
            principal.setUser(user);
            switch (isAdmin) {
                case 0:
                    principal.setAdmin(true);
                    principal.chargeWelcome();
                    break;
                case 1:
                    principal.setAdmin(false);
                    principal.chargeWelcome();
                    break;
                default:
                    errorBox.setText("There's been a problem in the database, try it again later");
                    break;
            }
            principal.hideMenus();
        } else {
            errorBox.setText("User or password is wrong");
        }
    }

    public void clear() {
        fxUser.clear();
        passBox.clear();
        errorBox.setText("");
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
