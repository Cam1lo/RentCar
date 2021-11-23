package cu.edu.cujae.carRent.visuals.pages.contracts;

import cu.edu.cujae.carRent.dtos.ContractDto;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.pages.contracts.payments.Payments;
import cu.edu.cujae.carRent.visuals.pages.contracts.updateForm.UpdateForm;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.StringFormatters;
import cu.edu.cujae.carRent.visuals.pages.contracts.addForm.AddForm;
import cu.edu.cujae.carRent.visuals.pages.contracts.deleteConfirm.DeleteConfirm;
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

public class Contracts {
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
    private TableView<ContractDto> table;
    @FXML
    private TableColumn<ContractDto, String> touristColumn;
    @FXML
    private TableColumn<ContractDto, String> carColumn;
    @FXML
    private TableColumn<ContractDto, String> datesColumn;
    @FXML
    private TableColumn<ContractDto, Integer> extensionColumn;
    @FXML
    private TableColumn<ContractDto, String> paymentColumn;
    @FXML
    private TableColumn<ContractDto, String> billColumn;
    @FXML
    private TableColumn<ContractDto, String> specialBill;
    @FXML
    private TableColumn<ContractDto, String> driverColumn;
    @FXML
    private TableColumn<ContractDto, Float> totalAmountColumn;


    private ContractDto selected;
    private AnchorPane parentApContent;

    public void onInit(ArrayList<ContractDto> items, AnchorPane parentApContent) {
        this.parentApContent = parentApContent;
        update_button.setDisable(true);
        delete_button.setDisable(true);

        touristColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTourist().getIdPassport()));
        carColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCar().getCarID()));
        datesColumn.setCellValueFactory(data ->
                new SimpleStringProperty(StringFormatters.twoDatesToString(
                        data.getValue().getStartingDate(),
                        data.getValue().getFinalDate()
                )));
        extensionColumn.setCellValueFactory(new PropertyValueFactory<>("extension"));
        paymentColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPayment().getPayment()));
        billColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getBill().getAmount())));
        specialBill.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getBill().getSpecialAmount())));
        driverColumn.setCellValueFactory(data -> {
            if (data.getValue().getDriver() == null) {
                return new SimpleStringProperty("-");
            } else
                return new SimpleStringProperty(data.getValue().getDriver().getId());
        });
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

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

    public void openUpdateForm() throws IOException, SQLException, ClassNotFoundException {
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

    public void openAddForm() throws IOException, SQLException, ClassNotFoundException {
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

    public void loadPayments() throws IOException, NoSuchFieldException, IllegalAccessException, SQLException {
        Payments payments = (Payments) ScenesManager.changeApContentTo(this.parentApContent, "payments");
        payments.onInit(ServicesLocator.getPaymentsServices().listPaymaent(), this.parentApContent);
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        table.setItems(FXCollections.observableList(ServicesLocator.getContractServices().listContract()));
    }
}
