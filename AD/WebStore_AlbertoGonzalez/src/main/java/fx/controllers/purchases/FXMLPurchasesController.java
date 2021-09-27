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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLPurchasesController implements Initializable {


    @FXML
    private ComboBox customerBox;
    @FXML
    private ComboBox itemBox;
    @FXML
    private ListView purchaseList;
    @FXML
    private DatePicker dateBox;

    
    public void load()
    {
    }
    
    public void loadPurchasesList() {
    }

    public void loadItemsList() {

    }

    public void loadCustomersList() {
    }

    public void addPurchase() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
