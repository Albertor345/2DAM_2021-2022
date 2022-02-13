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
import model.Item;
import model.Purchase;
import model.Review;
import org.bson.types.ObjectId;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLAddReviewController implements Initializable {

    @FXML
    private ListView<Customer> customerList;
    @FXML
    private ListView<Purchase> purchasesList;
    @FXML
    private ComboBox<Integer> ratingBox;
    @FXML
    private TextField titleBox;
    @FXML
    private TextArea textBox;

    private FXMLPrincipalController principal;
    private Alert alert;

    public void loadPurchasesFromCustomer(MouseEvent mouseEvent) {
        Customer selectedCustomer = customerList.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            purchasesList.getItems().clear();
            purchasesList.getItems().addAll(selectedCustomer.getPurchases());
        }
    }

    public void addReview() {
        if (purchasesList.getSelectionModel().getSelectedItem() != null && customerList.getSelectionModel().getSelectedItem() != null) {
            Review review = Review.builder()
                    ._id(new ObjectId())
                    .title(titleBox.getText())
                    .rate(ratingBox.getSelectionModel().getSelectedItem())
                    .review(textBox.getText())
                    .purchaseID(purchasesList.getSelectionModel().getSelectedItem().get_id())
                    .customerDNI(customerList.getSelectionModel().getSelectedItem().get_id())
                    .date(LocalDate.now()).build();

            if (principal.getServicesItems().addReview(review, Item.builder()._id(new ObjectId(purchasesList.getSelectionModel().getSelectedItem().getItemID())).build())) {
                alert("Success", "Review added", Alert.AlertType.CONFIRMATION);
                clear();
            }

        } else {
            alert("Warning", "Select a purchase from a Customer first", Alert.AlertType.WARNING);
        }
    }

    private void clear() {
        purchasesList.getSelectionModel().clearSelection();
        customerList.getSelectionModel().clearSelection();
        ratingBox.getSelectionModel().clearSelection();
        textBox.clear();
        titleBox.clear();
    }

    public void loadCustomers(List<Customer> customerList) {
        this.customerList.getItems().clear();
        if (principal.isAdmin()) {
            this.customerList.getItems().addAll(customerList);
        } else {
            this.customerList.getItems().addAll(
                    customerList.stream()
                            .filter(customer -> customer.get_id().equals(principal.getUser().get_id())).collect(Collectors.toList()));
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
