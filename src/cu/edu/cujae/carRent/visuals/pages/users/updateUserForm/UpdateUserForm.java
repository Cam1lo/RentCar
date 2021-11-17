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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UpdateUserForm {
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField user_name;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField pass_confirmation;
    @FXML
    private Label error_label;

    private UserDto user;
    private Users parent;

    public void onInit(UserDto user, Users parent) {
        this.parent = parent;
        this.user = user;
        this.user_name.setText(user.getName());
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void updateUser() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String username = this.user_name.getText();
        String password = this.pass.getText();
        String passConfirm = this.pass_confirmation.getText();

        String errors = Validations.newUserValidation(username, password, passConfirm);

        if (errors.equals("")) {
            ServicesLocator.getUserServices().updateUser(this.user.getCode(), username, password, this.user.isAdmin());
        } else {
            this.error_label.setText(errors);
        }

        parent.refreshTable();
        cancel();
    }
}