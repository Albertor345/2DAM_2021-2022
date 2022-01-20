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
import java.util.ResourceBundle;

public class FXMLfindCustomerController implements Initializable {
    private FXMLPrincipalController principalController;
    private Alert alert;

    @FXML
    private TextField textFieldID;
    @FXML
    private ListView<Customer> customerList;

    public void searchById() {
        try {
            if (!textFieldID.getText().isEmpty()) {
                Customer customer = principalController.getServicesCustomers().get(Customer.builder()
                        .id(Integer.parseInt(textFieldID.getText()))
                        .build());
                if (customer != null) {
                    customerList.getItems().clear();
                    customerList.getItems().add(customer);
                } else {
                    alert("Not found", "There aren't any items that match the ID you've introduced", Alert.AlertType.INFORMATION);
                }
            }
        } catch (NumberFormatException e) {
            alert("Warning", "The ID must be numeric", Alert.AlertType.WARNING);
        }
    }


    public void setPrincipalController(FXMLPrincipalController principalController) {
        this.principalController = principalController;
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

}
