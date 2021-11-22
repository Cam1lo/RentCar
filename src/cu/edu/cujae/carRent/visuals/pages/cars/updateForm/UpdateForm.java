package cu.edu.cujae.carRent.visuals.pages.cars.updateForm;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.dtos.CarStatusDto;
import cu.edu.cujae.carRent.dtos.ModelDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.cars.Cars;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private ComboBox<String> brand;
    @FXML
    private ComboBox<String> model;
    @FXML
    private ComboBox<String> status;

    private Cars parent;
    private CarDto selected;
    private Map<String, Integer> brandsMap = new HashMap<>();
    private Map<String, Integer> modelsMap = new HashMap<>();
    private Map<String, Integer> statusesMap = new HashMap<>();

    public void onInit(CarDto selected, Cars parent) throws SQLException {
        this.parent = parent;
        this.selected = selected;
        this.mileage.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100000, 0));

        ArrayList<CarStatusDto> statuses = ServicesLocator.getStatusServices().listStatus();
        ArrayList<String> statusesTextList = new ArrayList<>();
        for (CarStatusDto status : statuses) {
            statusesTextList.add(status.getStatusText());
            statusesMap.put(status.getStatusText(), status.getCode());
        }
        this.status.setItems(FXCollections.observableList(statusesTextList));

        ArrayList<BrandDto> brands = ServicesLocator.getBrandServices().listBrand();
        ArrayList<String> brandsTextList = new ArrayList<>();
        for (BrandDto b : brands) {
            if (!brandsTextList.contains(b.getBrandText())) {
                brandsTextList.add(b.getBrandText());
            }
            brandsMap.put(b.getBrandText(), b.getCode());
        }
        this.brand.setItems(FXCollections.observableList(brandsTextList));


        this.id.setText(selected.getCarID());
        this.color.setText(selected.getColor());
        this.mileage.getValueFactory().setValue(selected.getMileage());
        this.brand.setValue(selected.getBrand().getBrandText());
        this.updateModels();
        this.model.setValue(selected.getBrand().getModel().getModelText());
        this.status.setValue(selected.getStatus().getStatusText());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException {

        String id = this.id.getText();
        String color = this.color.getText();
        double mileage = this.mileage.getValue();
        int brandVal = this.brandsMap.get(this.brand.getValue());
        int modelVal = this.modelsMap.get(this.model.getValue());
        int status = this.statusesMap.get(this.status.getValue());

        BrandDto brand = ServicesLocator.getBrandServices().getBrandByText(this.brand.getValue(), this.model.getValue());
//        String errors = Validations.newUserValidation(username, password, passConfirm);

        ServicesLocator.getCarsServices().updateCar(this.selected.getCode(), id, status, brand.getCode(), color, mileage);


        this.parent.refreshTable();
        cancel();
    }

    public void updateModels() throws SQLException {
        this.model.setDisable(false);

        ArrayList<ModelDto> models = ServicesLocator.getModelServices().getModelByBrand(this.brand.getValue());
        ArrayList<String> modelsTextList = new ArrayList<>();
        for (ModelDto model : models) {
            modelsTextList.add(model.getModelText());
            modelsMap.put(model.getModelText(), model.getCode());
        }
        this.model.setItems(FXCollections.observableList(modelsTextList));
    }
}