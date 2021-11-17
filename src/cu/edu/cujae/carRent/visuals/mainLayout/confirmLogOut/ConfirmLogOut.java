package cu.edu.cujae.carRent.visuals.mainLayout.confirmLogOut;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ConfirmLogOut {
    @FXML
    private AnchorPane ap;

    private Stage stage;
    private Stage mainStage;

    public void onInit(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.setAlwaysOnTop(false);
    }

    public void cancel() {
        this.stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    public void confirm() throws IOException {
        this.mainStage.close();

        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../../login/Login.fxml"));

        Scene login_scene = new Scene(root);
        login_scene.setFill(Color.TRANSPARENT);
        Stage primaryStage = new Stage();
        primaryStage.setScene(login_scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        this.stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }
}
