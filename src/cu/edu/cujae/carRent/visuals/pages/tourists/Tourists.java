package cu.edu.cujae.carRent.visuals.pages.tourists;

import cu.edu.cujae.carRent.dtos.TouristDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.tourists.addForm.AddForm;
import cu.edu.cujae.carRent.visuals.pages.tourists.deleteConfirm.DeleteConfirm;
import cu.edu.cujae.carRent.visuals.pages.tourists.updateForm.UpdateForm;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tourists {
    @FXML
    private AnchorPane ap_content;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;
    @FXML
    private TableView<TouristDto> table;
    @FXML
    private TableColumn<TouristDto, String> nameColumn;
    @FXML
    private TableColumn<TouristDto, String> lastNameColumn;
    @FXML
    private TableColumn<TouristDto, String> passportColumn;
    @FXML
    private TableColumn<TouristDto, Integer> ageColumn;
    @FXML
    private TableColumn<TouristDto, String> sexColumn;
    @FXML
    private TableColumn<TouristDto, String> phoneColumn;
    @FXML
    private TableColumn<TouristDto, String> countryColumn;

    private TouristDto selected;


    public void onInit(ArrayList<TouristDto> items) {
        update_button.setDisable(true);
        delete_button.setDisable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        passportColumn.setCellValueFactory(new PropertyValueFactory<>("idPassport"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        table.setItems(FXCollections.observableList(items));

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                update_button.setDisable(false);
                delete_button.setDisable(false);
                this.selected = newSelection;
            } else {
                update_button.setDisable(true);
                delete_button.setDisable(true);
            }
        });

    }

    public void resetFocus() {
        table.getSelectionModel().clearSelection();
    }

    public void openUpdateForm() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./updateForm/UpdateForm.fxml"));
        Parent root = loader.load();
        UpdateForm controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        controller.onInit(this.selected, this);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }

    public void openAddForm() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./addForm/AddForm.fxml"));
        Parent root = loader.load();
        AddForm controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        controller.onInit(this);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }

    public void openDeleteConfirm() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteConfirm/DeleteConfirm.fxml"));
        Parent root = loader.load();
        DeleteConfirm controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        controller.onInit(this.selected, this);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        table.setItems(FXCollections.observableList(ServicesLocator.getTouristServices().listTourist()));
    }
}
