package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import fx.controllers.reviews.FXMLReviewDataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.log4j.Log4j2;
import model.Item;
import model.Review;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
public class FXMLItemsList implements Initializable {

    @FXML
    private Button clearButton;
    @FXML
    private TableView<Review> tableViewReviews;
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

    private Stage stage;
    private BorderPane stageBorderPane;

    private FXMLLoader fxmlLoaderReviewData;
    private AnchorPane reviewData;
    private FXMLReviewDataController reviewDataController;

    private FXMLPrincipalController principal;
    private Alert alert;

    @Inject
    public FXMLItemsList(FXMLLoader fxmlLoaderReviewData) {
        this.fxmlLoaderReviewData = fxmlLoaderReviewData;
    }

    @FXML
    private void showReviewData(MouseEvent actionEvent) {
        stageBorderPane.setCenter(reviewData);
        stage.setOnCloseRequest(event -> {});
        stage.setOnShown(event -> {
            reviewDataController.addReview(tableViewReviews.getSelectionModel().getSelectedItem());
            reviewDataController.setPrincipal(principal);
            reviewDataController.setStage(stage);
        });
        stage.show();
    }

    @FXML
    private void orderAsc(ActionEvent actionEvent) {
        if (radioButtonDate.getToggleGroup().selectedToggleProperty().getValue() == null) {
            radioButtonRating.setSelected(true);
        }
        tableViewReviews.getItems().clear();
        tableViewReviews.getItems().addAll(principal.getServicesReviews().orderBy(
                tableViewItems.getSelectionModel().getSelectedItem(),
                radioButtonAsc.isSelected(),
                radioButtonRating.isSelected()));
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

    private void preload() {
        try {
            if (reviewData == null) {
                reviewData = fxmlLoaderReviewData.load(getClass().getResourceAsStream("/fxml/reviews/FXMLReviewData.fxml"));
                reviewDataController = fxmlLoaderReviewData.getController();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            alert("Error", "error pre-loading review data", Alert.AlertType.ERROR);
        }

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

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stageBorderPane = new BorderPane();
        stage.setScene(new Scene(stageBorderPane));
        stage.setResizable(false);
        preload();

    }
}
