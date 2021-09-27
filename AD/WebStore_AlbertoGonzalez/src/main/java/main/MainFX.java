package main;

import gui.main.StartupScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;

public class MainFX {
    FXMLLoader fxmlLoader;

    @Inject
    public MainFX(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void start(@Observes @StartupScene Stage stage) {
        try {
            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream("/fxml/mainPane.fxml"));
            stage.setScene(new Scene(fxmlParent, 300, 100));
            stage.setTitle("JavaFX");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//--module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml

}
