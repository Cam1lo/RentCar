package cu.edu.cujae.carRent.visuals.pages.drivers.deleteConfirm;

import cu.edu.cujae.carRent.dtos.DriverDto;
import cu.edu.cujae.carRent.dtos.TouristDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.drivers.Drivers;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private DriverDto selected;
    private Drivers parent;

    public void onInit(DriverDto user, Drivers parent) {
        this.parent = parent;
        this.selected = user;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getDriverServices().deleteDriver(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}