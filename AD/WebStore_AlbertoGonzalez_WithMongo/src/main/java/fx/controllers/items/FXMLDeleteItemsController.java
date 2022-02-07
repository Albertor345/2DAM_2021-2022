package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Item;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXMLDeleteItemsController implements Initializable {
    Alert alert;
    private FXMLPrincipalController principalController;
    @FXML
    private ListView<Item> listViewItems;

    @FXML
    private void deleteItem(ActionEvent actionEvent) {
        Item item = listViewItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            if (!principalController.getServicesItems().checkItemPurchases(item)) {
                principalController.getServicesItems().delete(item);
                listViewItems.getItems().remove(item);
                listViewItems.refresh();
            } else {
                alert.setContentText("This item has purchases related to it, do you want to continue?");
                Optional<ButtonType> decision = alert.showAndWait();
                if (decision.get().equals(ButtonType.OK)) {
                    if (principalController.getServicesItems().deleteAllPurchasesFromAnItem(item)) {
                        listViewItems.getItems().remove(item);
                        listViewItems.refresh();
                        alert("Success", "The item has been deleted alongside its related purchases", Alert.AlertType.INFORMATION);
                    } else {
                        alert("Error", "The item has reviews or there's been an unexpected problem", Alert.AlertType.ERROR);
                    }
                } else {
                    alert("Aborted", "Task aborted", Alert.AlertType.INFORMATION);
                }
            }
        } else {
            alert("Warning", "Choose an item first", Alert.AlertType.WARNING);
        }
    }

    public void setPrincipal(FXMLPrincipalController principalController) {
        this.principalController = principalController;
    }


    public void loadList(List<Item> allItems) {
        if (!listViewItems.getItems().isEmpty()) {
            listViewItems.getItems().clear();
        }
        listViewItems.getItems().addAll(allItems);
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
