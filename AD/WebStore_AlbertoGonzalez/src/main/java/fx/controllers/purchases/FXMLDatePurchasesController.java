/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import services.PurchasesServices;


/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLDatePurchasesController implements Initializable {

    @FXML
    private DatePicker dateBox;
    @FXML
    private ListView purchaseList;

    public void loadPurchasesList() {
            }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void searchByDate(ActionEvent actionEvent) {
    }
}
