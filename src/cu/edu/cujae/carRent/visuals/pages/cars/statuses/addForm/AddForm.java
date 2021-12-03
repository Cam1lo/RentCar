package cu.edu.cujae.carRent.visuals.pages.cars.statuses.addForm;

import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Error;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.visuals.pages.cars.statuses.Statuses;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private TextField status;
    @FXML
    private Label error_label;

    private Statuses parent;


    public void onInit(Statuses parent) throws SQLException {
        this.parent = parent;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String statusText = this.status.getText();

        Error error = Validations.noEmptyStringValidation(
                new ArrayList<>(Arrays.asList(statusText)
                ));

        if (error.getErrorMsg() == null) {
            ServicesLocator.getStatusServices().insertStatus(statusText);
            this.parent.refreshTable();
            cancel();
        } else {
            this.error_label.setText(error.getErrorMsg());
        }
    }
}