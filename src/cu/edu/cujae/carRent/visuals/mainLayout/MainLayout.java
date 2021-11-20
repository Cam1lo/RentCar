package cu.edu.cujae.carRent.visuals.mainLayout;


import cu.edu.cujae.carRent.dtos.DriverDto;
import cu.edu.cujae.carRent.dtos.TouristDto;
import cu.edu.cujae.carRent.dtos.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.mainLayout.confirmLogOut.ConfirmLogOut;
import cu.edu.cujae.carRent.visuals.pages.drivers.Drivers;
import cu.edu.cujae.carRent.visuals.pages.tourists.Tourists;
import cu.edu.cujae.carRent.visuals.pages.users.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainLayout {
    @FXML
    private Label nav_item_contracts;
    @FXML
    private Label nav_item_cars;
    @FXML
    private Label nav_item_tourist;
    @FXML
    private Label nav_item_drivers;
    @FXML
    private Label nav_item_users;
    @FXML
    private Label log_out;
    @FXML
    private AnchorPane ap_container;
    @FXML
    private AnchorPane ap_content;

    public void onInit(UserDto user) {
        if (!user.isAdmin()) {
            this.nav_item_users.setVisible(false);
        }
    }

    public void confirmLogOut() throws IOException {
        Stage confirmLogOutStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./confirmLogOut/ConfirmLogOut.fxml"));
        Parent root = loader.load();
        ConfirmLogOut confirmLogOut = (ConfirmLogOut) loader.getController();
        confirmLogOut.onInit((Stage) ap_container.getScene().getWindow());

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        confirmLogOutStage.setScene(scene);
        confirmLogOutStage.initModality(Modality.APPLICATION_MODAL);
        confirmLogOutStage.initStyle(StageStyle.TRANSPARENT);
        confirmLogOutStage.setAlwaysOnTop(true);
        confirmLogOutStage.toFront();
        confirmLogOutStage.showAndWait();
    }

    public void loadUsers() throws SQLException,
            ClassNotFoundException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {

        ArrayList<UserDto> users = ServicesLocator.getUserServices().listUsers();

        setFocus(nav_item_users);
        Users users_controller = (Users) ScenesManager.changeApContentTo(this.ap_content, "users");
        users_controller.onInit(users);
    }

    public void loadTourists() throws SQLException,
            ClassNotFoundException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {

        ArrayList<TouristDto> tourists = ServicesLocator.getTouristServices().listTourist();

        setFocus(nav_item_tourist);
        Tourists tourists_controller = (Tourists) ScenesManager.changeApContentTo(this.ap_content, "tourists");
        tourists_controller.onInit(tourists);
    }

    public void loadDrivers() throws SQLException,
            ClassNotFoundException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {

        ArrayList<DriverDto> drivers = ServicesLocator.getDriverServices().listDriver();

        setFocus(nav_item_drivers);
        Drivers drivers_controller = (Drivers) ScenesManager.changeApContentTo(this.ap_content, "drivers");
        drivers_controller.onInit(drivers, this.ap_content);
    }

    public void setFocus(Label selectedLabel) {
        nav_item_contracts.getStyleClass().remove("active-nav-item");
        nav_item_cars.getStyleClass().remove("active-nav-item");
        nav_item_tourist.getStyleClass().remove("active-nav-item");
        nav_item_drivers.getStyleClass().remove("active-nav-item");
        nav_item_users.getStyleClass().remove("active-nav-item");
        selectedLabel.getStyleClass().add("active-nav-item");
    }
}
