package cu.edu.cujae.carRent.visuals.pages.contracts.payments.updateForm;

import cu.edu.cujae.carRent.dtos.PaymentsDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.contracts.payments.Payments;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UpdateForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField payment;
    @FXML
    private Label error_label;

    private PaymentsDto selected;
    private Payments parent;

    public void onInit(PaymentsDto selected, Payments parent) throws SQLException {
        this.selected = selected;
        this.parent = parent;

        this.payment.setText(selected.getPayment());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String paymentText = this.payment.getText();

        ServicesLocator.getPaymentsServices().updatePayment(this.selected.getCode(), paymentText);

        this.parent.refreshTable();
        cancel();
    }
}