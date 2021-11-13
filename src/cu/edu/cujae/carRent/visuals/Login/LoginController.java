package cu.edu.cujae.carRent.visuals.Login;

import cu.edu.cujae.carRent.dot.UserDto;
import cu.edu.cujae.carRent.services.ServicesLocator;
import cu.edu.cujae.carRent.utils.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {
    @FXML
    Button login_button;
    @FXML
    TextField user_name;
    @FXML
    PasswordField user_password;
    @FXML
    Label error_label;
    @FXML
    AnchorPane ap;
    @FXML
    ImageView close_icon;

    Stage stage;
    Double xOffset;
    Double yOffset;

    public void apMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    public void apMousePressed(MouseEvent event) {
        this.stage = (Stage) ap.getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    public void login(ActionEvent e) throws IOException, SQLException {
        String username = this.user_name.getText();
        String password = this.user_password.getText();

        String errors = Validations.loginValidation(username, password);

        if (errors.equals("")) {
            /*UserDto user = ServicesLocator.getUserServices().authentication(username, password);

            if(user)

            this.stage = (Stage) ap.getScene().getWindow();
            this.stage.close();*/

        }
    }

    public void initMainLayout() throws IOException {
        Parent mainLayout = FXMLLoader.load(getClass().getResource("../MainLayout/MainLayout.fxml"));

        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(mainLayout));
        mainStage.setMaximized(true);
        mainStage.show();
    }

    public void closeApplication(MouseEvent e) {
        System.exit(0);
    }
}
