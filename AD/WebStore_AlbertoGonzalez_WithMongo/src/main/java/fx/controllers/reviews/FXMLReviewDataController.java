package fx.controllers.reviews;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Review;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLReviewDataController implements Initializable {
    @FXML
    private TableView<Review> tableViewReview;
    @FXML
    private TableColumn tableColumnTitle;
    @FXML
    private TableColumn tableColumnDate;
    @FXML
    private TableColumn tableColumnDescription;
    @FXML
    private TableColumn tableColumnRating;

    private Stage stage;
    private FXMLPrincipalController principal;
    private Alert alert;


    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }

    public void addReview(Review review) {
        tableViewReview.getItems().clear();
        tableViewReview.getItems().add(review);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("review"));
    }
}
