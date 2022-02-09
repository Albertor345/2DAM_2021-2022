/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.sales;


import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import model.Customer;
import model.Item;
import model.Purchase;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class FXMLAddSaleController implements Initializable {
    FXMLPrincipalController principalController;
    Alert alert;
    @FXML
    private ComboBox<Customer> customerBox;
    @FXML
    private ComboBox<Item> itemBox;
    @FXML
    private DatePicker dateBox;


    @FXML
    private void addPurchase() {
        Item item = itemBox.getSelectionModel().getSelectedItem();
        Customer customer = customerBox.getSelectionModel().getSelectedItem();
        if (item != null && customer != null) {
            Purchase purchase = Purchase.builder()
                    .id(principalController.getServicesSales().getAll().size())
                    .customer(customer)
                    .item(item)
                    .date(dateBox.getValue()).build();

            if (principalController.getServicesSales().add(purchase)) {
                alert("Success", "Purchase added successfully", Alert.AlertType.INFORMATION);
                clear();
            } else {
                alert("Error", "There's been an error while adding the purchase, try it later", Alert.AlertType.ERROR);
            }
        }
    }

    public void load(List<Item> items, List<Customer> customers) {
        if (principalController.isAdmin()) {
            loadCustomersList(customers);
        } else {
            loadCustomersList(customers.stream()
                    .filter(customer -> customer.getId() == principalController.getUser().getId())
                    .collect(Collectors.toList()));
        }
        loadItemsList(items);
    }

    public void loadItemsList(List<Item> items) {
        if (!itemBox.getItems().isEmpty()) {
            itemBox.getItems().clear();
        }
        itemBox.getItems().addAll(items);
    }

    public void loadCustomersList(List<Customer> customers) {
        if (!customerBox.getItems().isEmpty()) {
            customerBox.getItems().clear();
        }
        customerBox.getItems().addAll(customers);
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void clear() {
        customerBox.getSelectionModel().clearSelection();
        itemBox.getSelectionModel().clearSelection();
        dateBox.setValue(null);
    }


    public void setPrincipal(FXMLPrincipalController principal) {
        this.principalController = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
}
