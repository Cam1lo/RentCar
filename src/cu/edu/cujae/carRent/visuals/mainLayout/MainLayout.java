package cu.edu.cujae.carRent.visuals.mainLayout;


import cu.edu.cujae.carRent.dot.TouristDto;
import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.visual.ScenesManager;
import cu.edu.cujae.carRent.visuals.mainLayout.confirmLogOut.ConfirmLogOut;
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

    private ScenesManager sm;
    private UserDto user;

    public void onInit(UserDto user) {
        this.sm = new ScenesManager();
        this.user = user;
        if (!this.user.isAdmin()) {
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

        this.nav_item_users.getStyleClass().add("active-nav-item");
        ArrayList<UserDto> users = ServicesLocator.getUserServices().listUsers();

        Users users_controller = (Users) this.sm.changeApContentTo(this.ap_content, "users", this.sm);
        users_controller.onInit(users);
    }

    public void loadTourists() throws SQLException,
            ClassNotFoundException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {

        this.nav_item_users.getStyleClass().add("active-nav-item");
        ArrayList<TouristDto> tourists = ServicesLocator.getTouristServices().listTourist();

        Tourists tourists_controller = (Tourists) this.sm.changeApContentTo(this.ap_content, "tourists", this.sm);
        tourists_controller.onInit(tourists);
    }
}
