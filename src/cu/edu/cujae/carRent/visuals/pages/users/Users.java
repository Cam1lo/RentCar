package cu.edu.cujae.carRent.visuals.pages.users;

import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.visuals.pages.users.addUserForm.AddUserForm;
import cu.edu.cujae.carRent.visuals.pages.users.updateUserForm.DeleteUserConfirm;
import cu.edu.cujae.carRent.visuals.pages.users.updateUserForm.UpdateUserForm;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Users {
    @FXML
    private AnchorPane ap_content;
    @FXML
    private Button update_user_button;
    @FXML
    private Button delete_user_button;
    @FXML
    private TableView<UserDto> table;
    @FXML
    private TableColumn<UserDto, String> nameColumn;
    @FXML
    private TableColumn<UserDto, String> passColumn;
    @FXML
    private TableColumn<UserDto, Boolean> isAdminColumn;

    private UserDto selectedUser;


    public void onInit(ArrayList<UserDto> users) {
        update_user_button.setDisable(true);
        delete_user_button.setDisable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        isAdminColumn.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isAdmin()));
        table.setItems(FXCollections.observableList(users));

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                update_user_button.setDisable(false);
                delete_user_button.setDisable(newSelection.isAdmin());

                this.selectedUser = newSelection;
            } else {
                update_user_button.setDisable(true);
                delete_user_button.setDisable(true);
            }
        });

    }

    public void resetFocus() {
        table.getSelectionModel().clearSelection();
    }

    public void openUpdateUserForm() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./updateUserForm/UpdateUserForm.fxml"));
        Parent root = loader.load();
        UpdateUserForm controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        controller.onInit(this.selectedUser, this);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }

    public void openAddUserForm() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./addUserForm/AddUserForm.fxml"));
        Parent root = loader.load();
        AddUserForm controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        controller.onInit(this);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }

    public void openDeleteUserConfirm() throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./deleteUserConfirm/DeleteUserConfirm.fxml"));
        Parent root = loader.load();
        DeleteUserConfirm controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        controller.onInit(this.selectedUser, this);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }

    public void refreshTable() throws SQLException, ClassNotFoundException {
        table.setItems(FXCollections.observableList(ServicesLocator.getUserServices().listUsers()));
    }
}
