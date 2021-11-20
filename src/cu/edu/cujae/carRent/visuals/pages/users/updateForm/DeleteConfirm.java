package cu.edu.cujae.carRent.visuals.pages.users.updateForm;

import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteConfirm {
    @FXML
    private AnchorPane ap;

    private UserDto selected;
    private Users parent;

    public void onInit(UserDto user, Users parent) {
        this.parent = parent;
        this.selected = user;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void delete() throws SQLException, ClassNotFoundException {
        ServicesLocator.getUserServices().deleteUser(this.selected.getCode());

        parent.refreshTable();
        cancel();
    }
}