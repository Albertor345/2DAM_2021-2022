/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Purchase;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class FXMLDeletePurchasesController implements Initializable {
    private FXMLPrincipalController principalController;
    private Alert alert;
    @FXML
    private ListView<Purchase> purchaseBox;

    @FXML
    private void deletePurchase() {
        Purchase purchase = purchaseBox.getSelectionModel().getSelectedItem();
        if (purchase != null) {
            if (principalController.getServicesItems().deletePurchase(purchase)) {
                alert("Success", "Purchase deleted", Alert.AlertType.WARNING);
                purchaseBox.getItems().remove(purchase);
                purchaseBox.refresh();
            } else {
                alert("Error", "This purchase has data related to it, and therefore, cannot be deleted", Alert.AlertType.WARNING);
            }
        }
    }

    public void loadPurchases() {
        if (!purchaseBox.getItems().isEmpty()) {
            purchaseBox.getItems().clear();
        }
        purchaseBox.getItems().addAll(principalController.getServicesItems().getAll().stream().flatMap(item -> item.getPurchases().stream()).collect(Collectors.toList()));
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
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

}
