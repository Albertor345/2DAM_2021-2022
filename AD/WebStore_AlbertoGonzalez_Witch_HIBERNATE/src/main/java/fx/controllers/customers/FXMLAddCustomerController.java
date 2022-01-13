/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLAddCustomerController implements Initializable {

    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField addressBox;


    private FXMLPrincipalController principalController;
    private Alert alert;

    @FXML
    private void addCustomer() {
        Customer customer = Customer.builder()
                .name(nameBox.getText())
                .phone(phoneBox.getText())
                .address(addressBox.getText())
                .build();
        if (principalController.getServicesCustomers().add(customer)) {
            alert("Success", "The customer has been added", Alert.AlertType.CONFIRMATION);
        } else {
            alert("Invalid", "There's been an error while adding the customer, try it later", Alert.AlertType.ERROR);
        }
        clear();
    }

    private void clear(){
        nameBox.clear();
        addressBox.clear();
        phoneBox.clear();
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void setPrincipal(FXMLPrincipalController principalController) {
        this.principalController = principalController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

}
