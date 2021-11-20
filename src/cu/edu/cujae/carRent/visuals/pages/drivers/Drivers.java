package cu.edu.cujae.carRent.visuals.pages.drivers;

import cu.edu.cujae.carRent.dtos.DriverDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.pages.drivers.addForm.AddForm;
import cu.edu.cujae.carRent.visuals.pages.drivers.categories.Categories;
import cu.edu.cujae.carRent.visuals.pages.drivers.deleteConfirm.DeleteConfirm;
import cu.edu.cujae.carRent.visuals.pages.drivers.updateForm.UpdateForm;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
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

public class Drivers {
    @FXML
    private AnchorPane ap_content;
    @FXML
    private Label categories;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;
    @FXML
    private TableView<DriverDto> table;
    @FXML
    private TableColumn<DriverDto, String> idColumn;
    @FXML
    private TableColumn<DriverDto, String> nameColumn;
    @FXML
    private TableColumn<DriverDto, String> lastNameColumn;
    @FXML
    private TableColumn<DriverDto, Integer> addressColumn;
    @FXML
    private TableColumn<DriverDto, String> categoryColumn;


    private DriverDto selected;
    private AnchorPane parentApContent;

    public void onInit(ArrayList<DriverDto> items, AnchorPane parentApContent) {
        this.parentApContent = parentApContent;
        update_button.setDisable(true);
        delete_button.setDisable(true);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory().getCategory()));

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

    public void loadCategories() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException {
        Categories cats = (Categories) ScenesManager.changeApContentTo(this.parentApContent, "categories");
        cats.onInit(ServicesLocator.getDriverCategoryServices().listDriverCategories(), this.parentApContent);
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        table.setItems(FXCollections.observableList(ServicesLocator.getDriverServices().listDriver()));
    }
}
