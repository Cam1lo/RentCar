package cu.edu.cujae.carRent.visuals.pages.cars.statuses.deleteConfirm;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.CarStatusDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.cars.statuses.Statuses;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private CarStatusDto selected;
    private Statuses parent;

    public void onInit(CarStatusDto selected, Statuses parent) {
        this.parent = parent;
        this.selected = selected;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getStatusServices().deleteStatus(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}