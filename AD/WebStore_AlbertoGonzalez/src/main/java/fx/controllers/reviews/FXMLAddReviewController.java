/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Purchase;
import model.Review;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLAddReviewController implements Initializable {

    @FXML
    private ListView<Customer> clientBox;
    @FXML
    private ListView<Purchase> purchaseBox;
    @FXML
    private ComboBox<Integer> ratingBox;
    @FXML
    private TextField titleBox;
    @FXML
    private TextArea textBox;
    @FXML
    private ListView<Review> reviewList;

    private FXMLPrincipalController principal;
    private Alert alert;

    public void loadPurchasesFromCustomer(MouseEvent mouseEvent) {

    }

    public void addReview() {

    }

    public void loadCustomers(List<Customer> customerList) {
        clientBox.getItems().clear();
        if (principal.isAdmin()) {
            clientBox.getItems().addAll(customerList);
        } else {
            clientBox.getItems().addAll(
                    customerList.stream()
                            .filter(customer -> customer.getId() == principal.getUser().getId()).collect(Collectors.toList()));
        }
    }

    public void loadPurchases(List<Purchase> purchaseList) {
        purchaseBox.getItems().clear();
        purchaseBox.getItems().addAll(purchaseList);
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
