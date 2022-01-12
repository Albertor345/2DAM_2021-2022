package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import fx.controllers.sales.FXMLPurchasesListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Customer;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLCustomerListController implements Initializable {
    @FXML
    private TableView<Customer> tableViewCustomers;
    @FXML
    private TableColumn tableColumnId;
    @FXML
    private TableColumn tableColumnName;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private TextField textFieldAddress;

    private Stage stage;
    private BorderPane stageBorderPane;

    private FXMLLoader fxmlLoaderPurchasesList;
    private AnchorPane purchasesList;
    private FXMLPurchasesListController purchasesListController;

    private FXMLPrincipalController principal;
    private Alert alert;

    @Inject
    public FXMLCustomerListController(FXMLLoader fxmlLoaderPurchasesList) {
        this.fxmlLoaderPurchasesList = fxmlLoaderPurchasesList;
    }

    public void loadCustomers(List<Customer> customerList) {
        if (!tableViewCustomers.getItems().isEmpty()) {
            tableViewCustomers.getItems().clear();
        }
        tableViewCustomers.getItems().addAll(customerList);
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
