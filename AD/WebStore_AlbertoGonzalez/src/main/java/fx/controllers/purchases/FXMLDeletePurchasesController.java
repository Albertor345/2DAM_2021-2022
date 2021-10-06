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
import java.util.List;
import java.util.ResourceBundle;


public class FXMLDeletePurchasesController implements Initializable {
    private FXMLPrincipalController principalController;
    private Alert alert;
    @FXML
    private ListView<Purchase> purchaseBox;


    public void loadPurchases(List<Purchase> purchaseList) {
        if (!purchaseBox.getItems().isEmpty()) {
            purchaseBox.getItems().clear();
        }
        purchaseBox.getItems().addAll(purchaseList);
    }

    public void deletePurchase() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

}
