package cu.edu.cujae.carRent.visuals.pages.users.addUserForm;

import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.utils.bdResponses.LoginResponse;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class AddUserForm {
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

    private Users parent;

    public void onInit(Users parent) {
        this.parent = parent;
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void addNewUser() throws IOException, SQLException, ClassNotFoundException {

        String username = this.user_name.getText();
        String password = this.pass.getText();
        String passConfirm = this.pass_confirmation.getText();

        String errors = Validations.newUserValidation(username, password, passConfirm);

        if (errors.equals("")) {
            ServicesLocator.getUserServices().insertUser(username, password, false);
        } else {
            this.error_label.setText(errors);
        }

        this.parent.refreshTable();
        cancel();
    }
}