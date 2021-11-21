package cu.edu.cujae.carRent.visuals.pages.cars;

import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.pages.cars.addForm.AddForm;
import cu.edu.cujae.carRent.visuals.pages.cars.deleteConfirm.DeleteConfirm;
import cu.edu.cujae.carRent.visuals.pages.cars.models.Models;
import cu.edu.cujae.carRent.visuals.pages.cars.updateForm.UpdateForm;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class Cars {
    @FXML
    private AnchorPane ap_content;
    @FXML
    private Label models;
    @FXML
    private Label statuses;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;
    @FXML
    private TableView<CarDto> table;
    @FXML
    private TableColumn<CarDto, String> idColumn;
    @FXML
    private TableColumn<CarDto, String> colorColumn;
    @FXML
    private TableColumn<CarDto, String> modelColumn;
    @FXML
    private TableColumn<CarDto, String> statusColumn;
    @FXML
    private TableColumn<CarDto, Double> mileageColumn;


    private CarDto selected;
    private AnchorPane parentApContent;

    public void onInit(ArrayList<CarDto> items, AnchorPane parentApContent) {
        this.parentApContent = parentApContent;
        update_button.setDisable(true);
        delete_button.setDisable(true);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("CarID"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        modelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel().getModelText()));
        statusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus().getStatusText()));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

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

    public void openUpdateForm() throws IOException, SQLException {
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

    public void openAddForm() throws IOException, SQLException {
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

    public void loadModels() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException {
        Models models = (Models) ScenesManager.changeApContentTo(this.parentApContent, "models");
        models.onInit(ServicesLocator.getModelServices().listModel(), this.parentApContent);
    }

    public void loadStatuses() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException {
//        Statuses statuses = (Statuses) ScenesManager.changeApContentTo(this.parentApContent, "statuses");
//        statuses.onInit(ServicesLocator.getStatusServices().listStatus(), this.parentApContent);
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        table.setItems(FXCollections.observableList(ServicesLocator.getCarsServices().listCars()));
    }
}
