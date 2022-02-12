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
import java.util.List;
import java.util.ResourceBundle;

public class FXMLAddItemsController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private ListView<Item> listViewItems;
    @FXML
    private TextField textFieldCompany;


    private FXMLPrincipalController principal;
    private Alert alert;


    @FXML
    private void addItem(ActionEvent actionEvent) {
        try {
            Item item = Item.builder()
                    .name(textFieldName.getText())
                    .company(textFieldCompany.getText())
                    .price(Integer.parseInt(textFieldPrice.getText()))
                    .build();
            if (principal.getServicesItems().add(item)) {
                listViewItems.getItems().add(item);
                listViewItems.refresh();
                alert("Congrats", "Item added!!", Alert.AlertType.CONFIRMATION);
            } else {
                alert("Something went wrong", "There's been a problem when adding the item", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            alert("Invalid", "The id must be numeric", Alert.AlertType.WARNING);
            e.printStackTrace();
        }
        clear();
    }

    public void loadList(List<Item> items) {
        if (!listViewItems.getItems().isEmpty()) {
            listViewItems.getItems().clear();
        }
        listViewItems.getItems().addAll(items);
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    private void clear(){
        textFieldCompany.clear();
        textFieldName.clear();
        textFieldPrice.clear();
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
}
