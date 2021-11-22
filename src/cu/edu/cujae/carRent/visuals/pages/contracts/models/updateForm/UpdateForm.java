package cu.edu.cujae.carRent.visuals.pages.contracts.models.updateForm;

import cu.edu.cujae.carRent.dtos.ModelDto;
import cu.edu.cujae.carRent.visuals.pages.contracts.models.Models;
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
    private TextField model;
    @FXML
    private Label error_label;

    private ModelDto selected;
    private Models parent;

    public void onInit(ModelDto selected, Models parent) {
        this.parent = parent;
        this.selected = selected;
        this.model.setText(selected.getModelText());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String modelText = this.model.getText();

//        ServicesLocator.getModelServices().updateModel(this.selected.getCode(), modelText);

        parent.refreshTable();
        cancel();
    }
}