package cu.edu.cujae.carRent.visuals.pages.cars.statuses.updateForm;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.CarStatusDto;
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

public class UpdateForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField status;
    @FXML
    private Label error_label;

    private CarStatusDto selected;
    private Statuses parent;


    public void onInit(CarStatusDto selected, Statuses parent) throws SQLException {
        this.selected = selected;
        this.parent = parent;

        this.status.setText(selected.getStatusText());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String statusText = this.status.getText();

        Error error = Validations.noEmptyStringValidation(
                new ArrayList<>(Arrays.asList(statusText)
                ));

        if (error.getErrorMsg() == null) {
            ServicesLocator.getStatusServices().updateStatus(this.selected.getCode(), statusText);
            this.parent.refreshTable();
            cancel();
        } else {
            this.error_label.setText(error.getErrorMsg());
        }
    }
}