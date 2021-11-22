package cu.edu.cujae.carRent.visuals.pages.cars.deleteConfirm;

import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.dtos.PaymentsDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.cars.Cars;
import cu.edu.cujae.carRent.visuals.pages.contracts.payments.Payments;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;
    @FXML
    private Button delete_button;
    @FXML
    private Label delete_label;

    private CarDto selected;
    private Cars parent;

    public void onInit(CarDto selected, Cars parent) throws SQLException, ClassNotFoundException {
        this.parent = parent;
        this.selected = selected;

        if (!ServicesLocator.getCarsServices().canDelete(this.selected)) {
            delete_button.setDisable(true);
            delete_label.setText("You cannot delete this element because its been used by other or others elements.");
        }
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getDriverCategoryServices().deleteDriverCategory(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}