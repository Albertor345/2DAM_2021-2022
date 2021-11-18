package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLItemsList implements Initializable {

    @FXML
    private TableView<Item> tableViewItems;
    @FXML
    private TableColumn tableColumnName;
    @FXML
    private TableColumn tableColumnCompany;
    @FXML
    private TableColumn tableColumnPrice;

    private FXMLPrincipalController principal;
    private Alert alert;

    public void loadList(List<Item> items) {
        if (!tableViewItems.getItems().isEmpty()) {
            tableViewItems.getItems().clear();
        }
        tableViewItems.getItems().addAll(items);
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }


}
