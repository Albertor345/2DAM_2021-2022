/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Item;
import model.Review;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLfindReviewController implements Initializable {

    @FXML
    private ListView<Review> reviewList;
    @FXML
    private ComboBox<Item> itemBox;

    private FXMLPrincipalController principal;
    private Alert alert;

    public void loadItems(List<Item> itemList) {
        itemBox.getItems().clear();
        itemBox.getItems().addAll(itemList);
    }

    public void searchByItem() {
        Item item = itemBox.getSelectionModel().getSelectedItem();
        if (item != null) {
            reviewList.getItems().clear();
            reviewList.getItems().addAll(principal.getServicesReviews().getReviewsByItem(item));
        }
    }

    public void setPrincipalController(FXMLPrincipalController principalController) {
        this.principal = principalController;
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
