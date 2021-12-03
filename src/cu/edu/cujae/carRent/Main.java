package cu.edu.cujae.carRent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.IconView;


public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("./visuals/login/Login.fxml"));

        Scene login_scene = new Scene(root);
        login_scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(login_scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("CarRent");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/icon.jpg")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}