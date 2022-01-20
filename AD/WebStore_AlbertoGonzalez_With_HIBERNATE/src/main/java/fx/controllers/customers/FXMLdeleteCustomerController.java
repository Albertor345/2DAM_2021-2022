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
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FXMLdeleteCustomerController implements Initializable {
    private FXMLPrincipalController principalController;
    private Alert alert;

    @FXML
    private ListView<Customer> customerBox;


    @FXML
    private void deleteCustomer() {
        Customer customer = customerBox.getSelectionModel().getSelectedItem();
        if (customer != null) {
            if (principalController.getServicesCustomers().delete(customer)) {
                alert("Success", "Customer deleted", Alert.AlertType.CONFIRMATION);
                customerBox.getItems().remove(customer);
            }else{
                alert("Error", "This customer has data related to him, and therefore, it cannot be delted", Alert.AlertType.ERROR);
            }
        } else {
            alert("Warning", "Choose a customer first", Alert.AlertType.WARNING);
        }
    }

    public void loadCustomersList(List<Customer> customers) {
        customerBox.getItems().clear();
        customerBox.getItems().addAll(customers);
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
