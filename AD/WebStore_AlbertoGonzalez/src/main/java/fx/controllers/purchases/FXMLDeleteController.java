/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import services.PurchasesServices;
import model.Purchase;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLDeleteController implements Initializable {

    @FXML
    private ListView purchaseBox;


    public void loadPurchases() {
     }
    
    public void deletePurchase(){

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPurchases();
    }

}
