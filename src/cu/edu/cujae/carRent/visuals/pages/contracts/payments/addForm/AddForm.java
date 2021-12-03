package cu.edu.cujae.carRent.visuals.pages.contracts.payments.addForm;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.PaymentsDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Error;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.visuals.pages.contracts.payments.Payments;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField payment;
    @FXML
    private Label error_label;

    private Payments parent;

    public void onInit(Payments parent) throws SQLException {
        this.parent = parent;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String paymentText = this.payment.getText();


        Error error = Validations.noEmptyStringValidation(
                new ArrayList<>(Arrays.asList(paymentText)
                ));

        if (error.getErrorMsg() == null) {
            ServicesLocator.getPaymentsServices().insertPayment(paymentText);
            this.parent.refreshTable();
            cancel();
        } else {
            this.error_label.setText(error.getErrorMsg());
        }
    }
}