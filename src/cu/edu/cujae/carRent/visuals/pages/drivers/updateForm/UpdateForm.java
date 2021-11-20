package cu.edu.cujae.carRent.visuals.pages.drivers.updateForm;

import cu.edu.cujae.carRent.dtos.DriverDto;
import cu.edu.cujae.carRent.dtos.DriversCategoriesDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.drivers.Drivers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private TextArea address;
    @FXML
    private ComboBox<String> category;

    private DriverDto selected;
    private Drivers parent;
    private Map<String, Integer> categoriesMap = new HashMap<>();

    public void onInit(DriverDto selected, Drivers parent) throws SQLException {
        this.selected = selected;
        this.parent = parent;

        ArrayList<DriversCategoriesDto> categories = ServicesLocator.getDriverCategoryServices().listDriverCategories();
        ArrayList<String> categoriesTextList = new ArrayList<>();

        for (DriversCategoriesDto cat : categories) {
            categoriesTextList.add(cat.getCategory());
            categoriesMap.put(cat.getCategory(), cat.getCode());
        }

        this.category.setItems(FXCollections.observableList(categoriesTextList));

        this.id.setText(this.selected.getId());
        this.name.setText(this.selected.getName());
        this.last_name.setText(this.selected.getLastName());
        this.address.setText(this.selected.getAddress());
        this.category.setValue(this.selected.getCategory().getCategory());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException {

        String id = this.id.getText();
        String name = this.name.getText();
        String lastName = this.last_name.getText();
        String address = this.address.getText();
        int category = this.categoriesMap.get(this.category.getValue());

//        String errors = Validations.newUserValidation(username, password, passConfirm);

        ServicesLocator.getDriverServices().updateDriver(this.selected.getCode(), id, name, lastName, address, category);


        this.parent.refreshTable();
        cancel();
    }
}