package fx.controllers.sales;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.Sale;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLSalesListController implements Initializable {

    FXMLPrincipalController principalController;
    Alert alert;
    @FXML
    private TableView<Sale> tableViewSales;

    public void load(List<Sale> sales) {
        if (principalController.isAdmin()) {
            loadPurchasesList(principalController.getServicesSales().getAll());
        } else {
            loadPurchasesList(sales.stream()
                    .filter(purchase -> purchase.getCustomer().getId() == principalController.getUser().getId())
                    .collect(Collectors.toList()));
        }
    }

    public void loadPurchasesList(List<Sale> sales) {
        if (!tableViewSales.getItems().isEmpty()) {
            tableViewSales.getItems().clear();
        }
        tableViewSales.getItems().addAll(sales);
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principalController = principal;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
}
