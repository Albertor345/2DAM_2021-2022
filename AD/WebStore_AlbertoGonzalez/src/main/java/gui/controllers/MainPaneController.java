package gui.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.Constantes;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {
    private FXMLLoader fxmlLoaderPantalla;
    private AnchorPane pantalla;
    private PantallaController pantallaController;
    @FXML
    private BorderPane mainPane;
    private Alert alert;

    @Inject
    public MainPaneController(FXMLLoader fxmlLoaderPantalla) {
        this.fxmlLoaderPantalla = fxmlLoaderPantalla;
    }

    @FXML
    private void cargarPantalla() {
        try {
            if (pantalla == null) {
                pantalla = fxmlLoaderPantalla.load(getClass().getResourceAsStream("/fxml/pantalla.fxml"));
                pantallaController = fxmlLoaderPantalla.getController();
                pantallaController.setBorderPane(this);
                mainPane.setCenter(pantalla);
            }
            mainPane.setCenter(pantalla);
        } catch (Exception e) {
            alert("Error al cargar la pantalla" + e);
        }
    }

    private void alert(String texto) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle(Constantes.ALERT_ERROR);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        cargarPantalla();
    }
}
