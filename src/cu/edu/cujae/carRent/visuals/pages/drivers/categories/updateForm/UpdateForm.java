package cu.edu.cujae.carRent.visuals.pages.drivers.categories.updateForm;

import cu.edu.cujae.carRent.dtos.DriversCategoriesDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.drivers.categories.Categories;
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
    private TextField category;
    @FXML
    private Label error_label;

    private DriversCategoriesDto selected;
    private Categories parent;

    public void onInit(DriversCategoriesDto selected, Categories parent) {
        this.parent = parent;
        this.selected = selected;
        this.category.setText(selected.getCategory());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String category = this.category.getText();

        ServicesLocator.getDriverCategoryServices().insertDriverCategory(category);

        parent.refreshTable();
        cancel();
    }
}