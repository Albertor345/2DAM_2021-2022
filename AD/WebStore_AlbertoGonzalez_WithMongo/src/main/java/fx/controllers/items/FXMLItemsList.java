package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
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
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Log4j2
public class FXMLItemsList implements Initializable {
    @FXML
    private Button clearButton;
    @FXML
    private Label labelMinRate;
    @FXML
    private Label labelMinDate;
    @FXML
    private ComboBox<Integer> comboBoxMinRate;
    @FXML
    private RadioButton radioButtonDate;
    @FXML
    private RadioButton radioButtonRate;
    @FXML
    private DatePicker datePickerMinDate;
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
        if (tableViewReviews.getSelectionModel().getSelectedItem() != null) {
            stageBorderPane.setCenter(reviewData);
            stage.setOnCloseRequest(event -> {
            });
            stage.setOnShown(event -> {
                reviewDataController.addReview(tableViewReviews.getSelectionModel().getSelectedItem());
                reviewDataController.setPrincipal(principal);
                reviewDataController.setStage(stage);
            });
            stage.show();
        }

    }


    @FXML
    private void orderByRate(ActionEvent actionEvent) {
        if (comboBoxMinRate.getSelectionModel().getSelectedItem() != null) {
            if (radioButtonDesc.getToggleGroup().selectedToggleProperty().getValue() == null) {
                radioButtonAsc.setSelected(true);
            }
            tableViewReviews.getItems().clear();
            if (radioButtonAsc.isSelected()) {
                tableViewReviews.getItems().addAll(principal.getServicesItems().get(tableViewItems.getSelectionModel().getSelectedItem())
                        .getReviews().stream()
                        .filter(review -> review.getRate() >= (comboBoxMinRate.getSelectionModel().getSelectedItem() != null ? comboBoxMinRate.getSelectionModel().getSelectedItem() : 0))
                        .sorted(Comparator.comparing(Review::getRate))
                        .collect(Collectors.toList()));
            } else {
                tableViewReviews.getItems().addAll(principal.getServicesItems().get(tableViewItems.getSelectionModel().getSelectedItem())
                        .getReviews().stream()
                        .filter(review -> review.getRate() >= (comboBoxMinRate.getSelectionModel().getSelectedItem() != null ? comboBoxMinRate.getSelectionModel().getSelectedItem() : 0))
                        .sorted(Comparator.comparing(Review::getRate).reversed())
                        .collect(Collectors.toList()));
            }


        }
    }

    @FXML
    private void orderByDate(ActionEvent actionEvent) {
        if (radioButtonDesc.getToggleGroup().selectedToggleProperty().getValue() == null) {
            radioButtonAsc.setSelected(true);
        }
        tableViewReviews.getItems().clear();
        if (radioButtonAsc.isSelected()) {
            tableViewReviews.getItems().addAll(principal.getServicesItems().get(tableViewItems.getSelectionModel().getSelectedItem())
                    .getReviews().stream()
                    .filter(review -> review.getDate().isAfter(datePickerMinDate.getValue() != null ? datePickerMinDate.getValue() : LocalDate.now()))
                    .sorted((review, t1) -> review.getDate().isBefore(t1.getDate()) ? 1 : 0)
                    .collect(Collectors.toList()));
        } else {
            tableViewReviews.getItems().addAll(principal.getServicesItems().get(tableViewItems.getSelectionModel().getSelectedItem())
                    .getReviews().stream()
                    .filter(review -> review.getDate().isAfter(datePickerMinDate.getValue() != null ? datePickerMinDate.getValue() : LocalDate.now()))
                    .sorted((review, t1) -> review.getDate().isBefore(t1.getDate()) ? 0 : 1)
                    .collect(Collectors.toList()));
        }


    }

    @FXML
    private void hideRate(ActionEvent actionEvent) {
        labelMinRate.setDisable(false);
        comboBoxMinRate.setDisable(false);
        labelMinDate.setDisable(true);
        datePickerMinDate.setDisable(true);
    }

    @FXML
    private void hideDate(ActionEvent actionEvent) {
        labelMinDate.setDisable(false);
        datePickerMinDate.setDisable(false);
        labelMinRate.setDisable(true);
        comboBoxMinRate.setDisable(true);
    }

    @FXML
    private void hideCardinals(ActionEvent actionEvent) {
        radioButtonAsc.setDisable(false);
        radioButtonDesc.setDisable(false);
        if (radioButtonRate.isSelected()) {
            hideRate(actionEvent);
        } else {
            hideDate(actionEvent);
        }
    }

    @FXML
    private void loadItemData(MouseEvent mouseEvent) {
        Item item = tableViewItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            radioButtonRate.setDisable(false);
            radioButtonDate.setDisable(false);
            loadReviewsByItem(new ActionEvent());
            loadData(item);
        } else {
            alert("Item required", "Select a Month from the combo box to proceed", Alert.AlertType.WARNING);
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
            if (item.getReviews() != null) {
                tableViewReviews.getItems().addAll(item.getReviews());
            }
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
        comboBoxMinRate.getSelectionModel().clearSelection();
        datePickerMinDate.setValue(null);
        radioButtonRate.setDisable(true);
        radioButtonDate.setDisable(true);
        radioButtonAsc.setDisable(true);
        radioButtonDesc.setDisable(true);
        labelMinRate.setDisable(true);
        comboBoxMinRate.setDisable(true);
        labelMinDate.setDisable(true);
        datePickerMinDate.setDisable(true);
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
                reviewData = fxmlLoaderReviewData.load(getClass().getResourceAsStream("/fxml/items/FXMLReviewData.fxml"));
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
        tableColumnRating.setCellValueFactory(new PropertyValueFactory<>("rate"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        alert = new Alert(Alert.AlertType.CONFIRMATION);

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stageBorderPane = new BorderPane();
        stage.setScene(new Scene(stageBorderPane));
        stage.setResizable(false);

        comboBoxMinRate.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        preload();
    }


}
