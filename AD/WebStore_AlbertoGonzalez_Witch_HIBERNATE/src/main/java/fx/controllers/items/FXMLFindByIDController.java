package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLFindByIDController implements Initializable {
    private FXMLPrincipalController principalController;
    private Alert alert;

    @FXML
    private ListView<Item> listViewItem;
    @FXML
    private TextField textFieldID;

    public void getItem(ActionEvent actionEvent) {
        try {
            if (!textFieldID.getText().isEmpty()) {
                Item item = principalController.getServicesItems().get(Item.builder()
                                .id(Integer.parseInt(textFieldID.getText()))
                                .build());
                if (item != null) {
                    listViewItem.getItems().clear();
                    listViewItem.getItems().add(item);
                } else {
                    alert("Not found", "There aren't any items that match the ID you've introduced", Alert.AlertType.INFORMATION);
                }
            }
        } catch (NumberFormatException e) {
            alert("Warning", "The ID must be numeric", Alert.AlertType.WARNING);
        }
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
