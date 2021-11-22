package cu.edu.cujae.carRent.visuals.pages.contracts.payments.deleteConfirm;

import cu.edu.cujae.carRent.dtos.ModelDto;
import cu.edu.cujae.carRent.dtos.PaymentsDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.contracts.payments.Payments;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private PaymentsDto selected;
    private Payments parent;

    public void onInit(PaymentsDto selected, Payments parent) {
        this.parent = parent;
        this.selected = selected;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getModelServices().deleteModel(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}