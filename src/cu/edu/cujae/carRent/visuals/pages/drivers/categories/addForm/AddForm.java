package cu.edu.cujae.carRent.visuals.pages.drivers.categories.addForm;

import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Error;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.visuals.pages.drivers.categories.Categories;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField category;
    @FXML
    private Label error_label;

    private Categories parent;

    public void onInit(Categories parent) {
        this.parent = parent;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String categoryText = this.category.getText();

        ServicesLocator.getDriverCategoryServices().insertDriverCategory(categoryText);

        Error error = Validations.noEmptyStringValidation(
                new ArrayList<>(Arrays.asList(categoryText)
                ));

        if (error.getErrorMsg() == null) {
            ServicesLocator.getDriverCategoryServices().insertDriverCategory(categoryText);
            this.parent.refreshTable();
            cancel();
        } else {
            this.error_label.setText(error.getErrorMsg());
        }
    }
}