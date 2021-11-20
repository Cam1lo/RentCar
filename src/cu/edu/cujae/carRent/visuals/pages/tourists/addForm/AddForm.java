package cu.edu.cujae.carRent.visuals.pages.tourists.addForm;

import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.utils.rawData.Country;
import cu.edu.cujae.carRent.visuals.pages.tourists.Tourists;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddForm {
    @FXML
    private AnchorPane ap;
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

    public void onInit(Tourists parent) {
        this.parent = parent;
        this.age.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        this.country.setItems(FXCollections.observableList(Country.getCountries()));
        this.sex.setItems(FXCollections.observableList(new ArrayList<String>() {{
            add("M");
            add("F");
        }}));
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws IOException, SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String name = this.name.getText();
        String lastName = this.last_name.getText();
        String passport = this.passport.getText();
        String phone = this.phone.getText();
        String sex = this.sex.getValue();
        int age = this.age.getValue();
        String country = this.country.getValue();

//        String errors = Validations.newUserValidation(username, password, passConfirm);

        ServicesLocator.getTouristServices().insertTourist(name, lastName, passport, country, sex, age, phone);


        this.parent.refreshTable();
        cancel();
    }
}