package cu.edu.cujae.carRent.visuals.pages.drivers.categories;

import cu.edu.cujae.carRent.dtos.DriversCategoriesDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.pages.drivers.Drivers;
import cu.edu.cujae.carRent.visuals.pages.drivers.categories.addForm.AddForm;
import cu.edu.cujae.carRent.visuals.pages.drivers.categories.deleteConfirm.DeleteConfirm;
import cu.edu.cujae.carRent.visuals.pages.drivers.categories.updateForm.UpdateForm;
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

public class Categories {
    @FXML
    private AnchorPane ap_content;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;
    @FXML
    private TableView<DriversCategoriesDto> table;
    @FXML
    private TableColumn<DriversCategoriesDto, String> categoryColumn;

    private DriversCategoriesDto selected;
    private AnchorPane parentApContent;

    public void onInit(ArrayList<DriversCategoriesDto> items, AnchorPane ap) {
        this.parentApContent = ap;
        update_button.setDisable(true);
        delete_button.setDisable(true);

        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

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

    public void loadDrivers() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        Drivers driversPage = (Drivers) ScenesManager.changeApContentTo(this.parentApContent, "drivers");
        driversPage.onInit(ServicesLocator.getDriverServices().listDriver(), this.parentApContent);
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        table.setItems(FXCollections.observableList(ServicesLocator.getDriverCategoryServices().listDriverCategories()));
    }
}
