package fx.controllers.sales;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Purchase;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLSalesListController implements Initializable {

    FXMLPrincipalController principalController;
    Alert alert;
    @FXML
    private TableView<Purchase> tableViewSales;
    @FXML
    private TableColumn tableColumnItem;
    @FXML
    private TableColumn tableColumnCustomer;
    @FXML
    private TableColumn tableColumnDate;
    @FXML
    private ComboBox<String> comboBoxOrderBy;
    @FXML
    private DatePicker datePickerInitialDate;
    @FXML
    private DatePicker datePickerFinalDate;

    @FXML
    private void orderPurchasesBy(ActionEvent actionEvent) {
        switch (comboBoxOrderBy.getSelectionModel().getSelectedItem()) {
            case "Items":
                loadSalesOrderedBy(true);
                break;
            case "Customers":
                loadSalesOrderedBy(false);
                break;
            case "Date":
                loadOrderedByDate();
                break;
            default:
                break;
        }
    }

    private void loadSalesOrderedBy(boolean orderBy) {
        loadPurchasesList(principalController.getServicesSales().getAllOrderedBy(orderBy));
    }

    private void loadOrderedByDate() {
        LocalDate initDate = datePickerInitialDate.getValue();
        LocalDate finalDate = datePickerFinalDate.getValue();
        if (initDate != null && finalDate != null) {
            if (!finalDate.isBefore(initDate)) {
                loadPurchasesList(principalController.getServicesSales().getAllOrderedByDate(initDate, finalDate));
            } else {
                alert("Warning", "Final date cannot be before the initial date", Alert.AlertType.WARNING);
            }
        }else{
            alert("Warning", "Choose the dates in between the purchases are located", Alert.AlertType.WARNING);
        }
    }

    public void load(List<Purchase> purchases) {
        if (principalController.isAdmin()) {
            loadPurchasesList(principalController.getServicesSales().getAll());
        } else {
            loadPurchasesList(purchases.stream()
                    .filter(purchase -> purchase.getCustomer().getId() == principalController.getUser().getId())
                    .collect(Collectors.toList()));
        }
    }

    public void loadPurchasesList(List<Purchase> purchases) {
        if (!tableViewSales.getItems().isEmpty()) {
            tableViewSales.getItems().clear();
        }
        tableViewSales.getItems().addAll(purchases);
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principalController = principal;
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        comboBoxOrderBy.getItems().addAll("Items","Customers","Date");
        tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tableColumnItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
