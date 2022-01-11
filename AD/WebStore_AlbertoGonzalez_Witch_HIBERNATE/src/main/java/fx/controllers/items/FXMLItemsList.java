package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Item;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLItemsList implements Initializable {

    @FXML
    private Button clearButton;
    @FXML
    private TableView tableViewReviews;
    @FXML
    private TableColumn tableColumnTitle;
    @FXML
    private TableColumn tableColumnRating;
    @FXML
    private TableColumn tableColumnDate;
    @FXML
    private RadioButton radioButtonAsc;
    @FXML
    private RadioButton radioButtonDesc;
    @FXML
    private RadioButton radioButtonRating;
    @FXML
    private RadioButton radioButtonDate;
    @FXML
    private TextField textFieldItemData;
    @FXML
    private TableView<Item> tableViewItems;
    @FXML
    private TableColumn tableColumnName;
    @FXML
    private TableColumn tableColumnCompany;

    private FXMLPrincipalController principal;
    private Alert alert;

    @FXML
    private void orderByDate(ActionEvent actionEvent) {
    }

    @FXML
    private void orderByRating(ActionEvent actionEvent) {
    }

    @FXML
    private void orderDesc(ActionEvent actionEvent) {
    }

    @FXML
    private void orderAsc(ActionEvent actionEvent) {
    }


    @FXML
    private void loadItemData(MouseEvent mouseEvent) {
        Item item = tableViewItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            loadReviewsByItem(new ActionEvent());
            loadData(item);
        } else {
            alert("Month required", "Select a Month from the combo box to proceed", Alert.AlertType.WARNING);
        }
    }

    private void loadData(Item item) {
        textFieldItemData.setText(principal.getServicesItems().getItemData(item));
    }

    @FXML
    private void loadReviewsByItem(ActionEvent actionEvent) {
        Item item = tableViewItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            tableViewReviews.setDisable(false);
            tableViewReviews.getItems().clear();
            tableViewReviews.getItems().addAll(
                    principal.getServicesReviews().getReviewsByItem(item));
        } else {
            alert("Item required", "Select an Item from the list to proceed", Alert.AlertType.WARNING);
            tableViewReviews.setDisable(true);
        }
    }

    @FXML
    private void clear(ActionEvent actionEvent) {
        tableViewItems.getSelectionModel().clearSelection();
        textFieldItemData.clear();
        tableViewReviews.getItems().clear();

    }

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


        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        alert = new Alert(Alert.AlertType.CONFIRMATION);

    }
}
