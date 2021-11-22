package cu.edu.cujae.carRent.visuals.pages.contracts.deleteConfirm;

import cu.edu.cujae.carRent.dtos.CarDto;
import cu.edu.cujae.carRent.dtos.ContractDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.contracts.Contracts;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private ContractDto selected;
    private Contracts parent;

    public void onInit(ContractDto selected, Contracts parent) {
        this.parent = parent;
        this.selected = selected;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getContractServices().deleteContract(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}