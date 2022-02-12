package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;

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

    private FXMLPrincipalController principal;
    private Alert alert;


    @FXML
    private void loadCustomerData(MouseEvent mouseEvent) {
        Customer customer = tableViewCustomers.getSelectionModel().getSelectedItem();
        if (customer != null) {
            textFieldPhone.setText(customer.getAddress().getPhone());
            textFieldAddress.setText(customer.getAddress().getAddress());
        }
    }

    public void loadCustomers(List<Customer> customerList) {
        clear();
        if (!tableViewCustomers.getItems().isEmpty()) {
            tableViewCustomers.getItems().clear();
        }
        tableViewCustomers.getItems().addAll(customerList);
    }

    private void clear() {
        textFieldPhone.clear();
        textFieldAddress.clear();
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("_id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
