package cu.edu.cujae.carRent.visuals.MainLayout;


import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.visuals.MainLayout.confirmLogOut.ConfirmLogOut;
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

public class MainLayout {
    @FXML
    private Label main_header;
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

    private UserDto user;

    public void onInit(UserDto user) {
        this.user = user;
        if(!this.user.isAdmin()) {
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
}
