package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Item;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDeleteItemsController implements Initializable {
    private FXMLPrincipalController principalController;
    @FXML
    private ListView listViewItems;

    @FXML
    private void deleteItem(ActionEvent actionEvent) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
