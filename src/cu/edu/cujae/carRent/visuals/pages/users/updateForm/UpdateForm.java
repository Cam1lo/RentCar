package cu.edu.cujae.carRent.visuals.pages.users.updateForm;

import cu.edu.cujae.carRent.dtos.RoleDto;
import cu.edu.cujae.carRent.dtos.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Error;
import cu.edu.cujae.carRent.utils.Validations;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UpdateForm {
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
    @FXML
    private ComboBox<String> role;


    private Map<String, Integer> rolesMap = new HashMap<>();
    private UserDto user;
    private Users parent;

    public void onInit(UserDto user, Users parent) throws SQLException {
        this.parent = parent;
        this.user = user;
        this.user_name.setText(user.getName());
        this.role.setValue(user.getRole().getRoleText());

        ArrayList<RoleDto> roles = ServicesLocator.getRoleServices().listRoles();
        ArrayList<String> rolesTextList = new ArrayList<>();
        for (RoleDto role : roles) {
            if (!role.getRoleText().equals("Administrator")) {
                rolesTextList.add(role.getRoleText());
                rolesMap.put(role.getRoleText(), role.getCode());
            }
        }
        this.role.setItems(FXCollections.observableList(rolesTextList));
    }

    public void cancel() {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void update() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        String username = this.user_name.getText();
        String password = this.pass.getText();
        String passConfirm = this.pass_confirmation.getText();

        Error error = Validations.noEmptyStringValidation(new ArrayList<>(Arrays.asList(username, passConfirm, password)));

        if (!password.equals(passConfirm) && error.getErrorMsg() == null) {
            error.setErrorMsg("Password and password confirm fields must be equals.");
        }

        if (error.getErrorMsg() == null) {
            ServicesLocator.getUserServices().updateUser(this.user.getCode(), username, password, ServicesLocator.getRoleServices().getRoleByText(this.role.getValue()));
            this.parent.refreshTable();
            cancel();
        } else {
            this.error_label.setText(error.getErrorMsg());
        }

    }
}