package fx.controllers.sales;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Sale;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLSalesListController implements Initializable {

    FXMLPrincipalController principalController;
    Alert alert;
    @FXML
    private TableView<Sale> tableViewSales;
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
            case "Item":
                loadSalesOrderedBy(true);
                break;
            case "Customer":
                loadSalesOrderedBy(false);
                break;
            default:
                loadOrderedByDate();
                break;
        }
    }

    private void loadSalesOrderedBy(boolean orderBy) {
        loadPurchasesList(principalController.getServicesSales().getAllOrderedBy(orderBy));
    }

    private void loadOrderedByDate() {
        Date initDate = Date.valueOf(datePickerInitialDate.getValue());
        Date finalDate = Date.valueOf(datePickerFinalDate.getValue());
        if (!finalDate.before(initDate)) {
            loadPurchasesList(principalController.getServicesSales().getAllOrderedByDate(initDate, finalDate));
        } else {
            alert("Warning", "Final date cannot be before the initial date", Alert.AlertType.WARNING);
        }
    }

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

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        tableColumnCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        tableColumnItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
