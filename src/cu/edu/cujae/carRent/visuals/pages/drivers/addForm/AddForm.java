package cu.edu.cujae.carRent.visuals.pages.drivers.addForm;

import cu.edu.cujae.carRent.dtos.DriversCategoriesDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.rawData.Country;
import cu.edu.cujae.carRent.visuals.pages.drivers.Drivers;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddForm {
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

    private Drivers parent;
    private Map<String, Integer> categoriesMap = new HashMap<>();

    public void onInit(Drivers parent) throws SQLException {
        this.parent = parent;

        ArrayList<DriversCategoriesDto> categories = ServicesLocator.getDriverCategoryServices().listDriverCategories();
        ArrayList<String> categoriesTextList = new ArrayList<>();

        for (DriversCategoriesDto cat : categories) {
            categoriesTextList.add(cat.getCategory());
            categoriesMap.put(cat.getCategory(), cat.getCode());
        }

        this.category.setItems(FXCollections.observableList(categoriesTextList));
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException {

        String id = this.id.getText();
        String name = this.name.getText();
        String lastName = this.last_name.getText();
        String address = this.address.getText();
        int category = this.categoriesMap.get(this.category.getValue());

//        String errors = Validations.newUserValidation(username, password, passConfirm);

        ServicesLocator.getDriverServices().insertDriver(id, name, lastName, address, category);


        this.parent.refreshTable();
        cancel();
    }
}