package cu.edu.cujae.carRent.visuals.pages.users.updateUserForm;

import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserConfirm {
    @FXML
    private AnchorPane ap;

    private UserDto user;
    private Users parent;

    public void onInit(UserDto user, Users parent) {
        this.parent = parent;
        this.user = user;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void deleteUser() throws SQLException, ClassNotFoundException {
        ServicesLocator.getUserServices().deleteUser(this.user.getCode());

        parent.refreshTable();
        cancel();
    }
}