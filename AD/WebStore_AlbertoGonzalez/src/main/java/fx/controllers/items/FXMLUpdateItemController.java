package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Item;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLUpdateItemController implements Initializable {
    @FXML
    private ComboBox<Item> comboBoxItems;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private TextField textFieldCompany;

    private FXMLPrincipalController principalController;
    private Alert alert;

    @FXML
    private void updateItem(ActionEvent actionEvent) {
        Item oldItem = comboBoxItems.getSelectionModel().getSelectedItem();
        try {
            if (oldItem != null) {
                Item newItem = Item.builder()
                        .id(oldItem.getId())
                        .name(textFieldName.getText())
                        .price(Double.parseDouble(textFieldPrice.getText()))
                        .company(textFieldCompany.getText())
                        .build();
                if (principalController.getServicesItems().update(newItem)) {
                    alert("Success", "Item updated", Alert.AlertType.CONFIRMATION);
                    comboBoxItems.getItems().remove(oldItem);
                    comboBoxItems.getItems().add(newItem);
                } else {
                    alert("Error", "Something went wrong", Alert.AlertType.ERROR);
                }
                clear();
            }
        } catch (NumberFormatException e) {
            alert("Warning", "Invalid data", Alert.AlertType.WARNING);
            e.printStackTrace();
        }
    }

    private void clear() {
        textFieldCompany.clear();
        textFieldName.clear();
        textFieldPrice.clear();
    }

    public void loadItems(List<Item> items) {
        comboBoxItems.getItems().clear();
        comboBoxItems.getItems().addAll(items);
    }

    public void setPrincipalController(FXMLPrincipalController principalController) {
        this.principalController = principalController;
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
    }
}
