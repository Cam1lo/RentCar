package cu.edu.cujae.carRent.visuals.pages.tourists.updateForm;

import cu.edu.cujae.carRent.dtos.TouristDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Error;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.utils.rawData.Country;
import cu.edu.cujae.carRent.visuals.pages.tourists.Tourists;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class UpdateForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private Label error_label;
    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField passport;
    @FXML
    private TextField phone;
    @FXML
    private Spinner<Integer> age;
    @FXML
    private ComboBox<String> sex;
    @FXML
    private ComboBox<String> country;

    private Tourists parent;
    private TouristDto selected;

    public void onInit(TouristDto selected, Tourists parent) {
        this.selected = selected;
        this.parent = parent;
        this.age.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        this.country.setItems(FXCollections.observableList(Country.getCountries()));
        this.sex.setItems(FXCollections.observableList(new ArrayList<String>() {{
            add("M");
            add("F");
        }}));

        this.name.setText(selected.getName());
        this.last_name.setText(selected.getLastName());
        this.passport.setText(selected.getIdPassport());
        this.phone.setText(selected.getTelephoneNumber());
        this.age.getValueFactory().setValue(selected.getAge());
        this.sex.setValue(selected.getSex());
        this.country.setValue(selected.getCountry());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String name = this.name.getText();
        String lastName = this.last_name.getText();
        String passport = this.passport.getText();
        String phone = this.phone.getText();
        String sex = this.sex.getValue();
        int age = this.age.getValue();
        String country = this.country.getValue();

        Error error = Validations.noEmptyStringValidation(
                new ArrayList<>(Arrays.asList(name, lastName, passport, phone, sex, country)
                ));

        if (!Validations.validateLetters(name) || !Validations.validateLetters(lastName)) {
            error.setErrorMsg("Name can only contain letters.");
        }
        if (!Validations.validatePassportId(passport)) {
            error.setErrorMsg("Passport must be in this format 'X123456'.");
        }

        if (error.getErrorMsg() == null) {
            ServicesLocator.getTouristServices().updateTourist(selected.getCode(), name, lastName, passport, country, sex, age, phone);
            this.parent.refreshTable();
            cancel();
        } else {
            this.error_label.setText(error.getErrorMsg());
        }
    }
}