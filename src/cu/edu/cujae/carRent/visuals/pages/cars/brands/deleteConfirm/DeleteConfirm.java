package cu.edu.cujae.carRent.visuals.pages.cars.brands.deleteConfirm;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.dtos.ModelDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.cars.brands.Brands;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private BrandDto selected;
    private Brands parent;

    public void onInit(BrandDto selected, Brands parent) {
        this.parent = parent;
        this.selected = selected;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getBrandServices().deleteBrand(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}