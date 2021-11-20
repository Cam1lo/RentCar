package cu.edu.cujae.carRent.visuals.pages.tourists.deleteConfirm;

import cu.edu.cujae.carRent.dot.TouristDto;
import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.tourists.Tourists;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private TouristDto selected;
    private Tourists parent;

    public void onInit(TouristDto user, Tourists parent) {
        this.parent = parent;
        this.selected = user;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getTouristServices().deleteTourist(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}