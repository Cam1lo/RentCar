package cu.edu.cujae.carRent.visuals.pages.contracts.models.addForm;

import cu.edu.cujae.carRent.dtos.BrandDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.contracts.models.Models;
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
import java.util.HashMap;
import java.util.Map;

public class AddForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField model;
    @FXML
    private ComboBox<String> brand;
    @FXML
    private Label error_label;

    private Models parent;
    private Map<String, Integer> brandMap = new HashMap<>();


    public void onInit(Models parent) throws SQLException {
        this.parent = parent;

        ArrayList<BrandDto> brands = ServicesLocator.getBrandServices().listBrand();
        ArrayList<String> brandsTextList = new ArrayList<>();
        for (BrandDto brand : brands) {
            brandsTextList.add(brand.getBrandText());
            brandMap.put(brand.getBrandText(), brand.getCode());
        }
        this.brand.setItems(FXCollections.observableList(brandsTextList));
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNew() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String modelText = this.model.getText();

//        ServicesLocator.getModelServices().insertModel( 0 ,modelText);

        this.parent.refreshTable();
        cancel();
    }
}