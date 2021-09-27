package gui.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaController implements Initializable {

    private MainPaneController borderPane;
    private Alert alert;


    public void setBorderPane(MainPaneController borderPane) {
        this.borderPane = borderPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}
