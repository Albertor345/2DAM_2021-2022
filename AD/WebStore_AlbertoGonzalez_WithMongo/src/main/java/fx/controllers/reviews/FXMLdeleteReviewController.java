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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Purchase;
import model.Review;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLdeleteReviewController implements Initializable {

    Alert alert;
    private FXMLPrincipalController principal;

    @FXML
    private ListView<Customer> listViewCustomers;
    @FXML
    private ListView<Review> listViewReviews;

    @FXML
    private void getAllReviewsFromCustomer(MouseEvent mouseEvent) {
        Customer customer = listViewCustomers.getSelectionModel().getSelectedItem();
        if (customer != null) {
            loadReviewList(customer);
        }
    }

    private void loadReviewList(Customer customer) {
        if (!listViewReviews.getItems().isEmpty()) {
            listViewReviews.getItems().clear();
        }
        listViewReviews.getItems().addAll(customer.getPurchases().stream().filter(purchase -> purchase.getReview() != null).map(Purchase::getReview).collect(Collectors.toList()));
    }

    @FXML
    private void deleteReview() {
        Review review = listViewReviews.getSelectionModel().getSelectedItem();
        if (review != null) {
            if (principal.getServicesItems().deleteReview(review)) {
                listViewReviews.getItems().remove(review);
                alert("Success", "Review Deleted Successfully", Alert.AlertType.CONFIRMATION);
            } else {
                alert("Error", "There's been a problem while processing your solicitude, try it again later", Alert.AlertType.CONFIRMATION);
            }
        }
    }

    public void loadCustomersList(List<Customer> customers) {
        if (!listViewCustomers.getItems().isEmpty()) {
            listViewCustomers.getItems().clear();
        }
        listViewCustomers.getItems().addAll(customers);
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void setPrincipal(FXMLPrincipalController principalController) {
        this.principal = principalController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
}
