package cu.edu.cujae.carRent.visuals.pages.cars.updateForm;

import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.dtos.CarStatusDto;
import cu.edu.cujae.carRent.dtos.ModelDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.cars.Cars;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField id;
    @FXML
    private TextField color;
    @FXML
    private Spinner<Double> mileage;
    @FXML
    private ComboBox<String> model;
    @FXML
    private ComboBox<String> status;

    private Cars parent;
    private CarDto selected;
    private Map<String, Integer> modelsMap = new HashMap<>();
    private Map<String, Integer> statusesMap = new HashMap<>();

    public void onInit(CarDto selected, Cars parent) throws SQLException {
        this.selected = selected;
        this.parent = parent;

        ArrayList<ModelDto> models = ServicesLocator.getModelServices().listModel();
        ArrayList<String> modelsTextList = new ArrayList<>();
        for (ModelDto model : models) {
            modelsTextList.add(model.getModel());
            modelsMap.put(model.getModel(), model.getCode());
        }
        this.model.setItems(FXCollections.observableList(modelsTextList));

        ArrayList<CarStatusDto> statuses = ServicesLocator.getStatusServices().listStatus();
        ArrayList<String> statusesTextList = new ArrayList<>();
        for (CarStatusDto status : statuses) {
            statusesTextList.add(status.getStatus());
            statusesMap.put(status.getStatus(), status.getCode());
        }
        this.status.setItems(FXCollections.observableList(statusesTextList));


        this.id.setText(selected.getCarID());
        this.color.setText(selected.getColor());
        this.mileage.getValueFactory().setValue(selected.getKm_driver());
        this.model.setValue(selected.getModel().getModel());
        this.status.setValue(selected.getStatus().getStatus());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException {

        String id = this.id.getText();
        String color = this.color.getText();
        double mileage = this.mileage.getValue();
        int model = this.modelsMap.get(this.model.getValue());
        int status = this.statusesMap.get(this.status.getValue());

//        String errors = Validations.newUserValidation(username, password, passConfirm);

//        ServicesLocator.getCarsServices().updateCar(this.selected.getCode(), id, status, model, color, mileage);


        this.parent.refreshTable();
        cancel();
    }
}