package cu.edu.cujae.carRent.visuals;

import cu.edu.cujae.carRent.services.ServicesLocator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Controller {
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

    public void login(ActionEvent e) {
        String error = "";

        if (this.user_name.getText().equals("")) {
            error = "User name field is required";
            if (this.user_password.getText().equals("")) {
                error = "User name and password fields are required";
            }
        } else if (this.user_password.getText().equals("")) {
            error = "Password field is required";
        }

        error_label.setText(error);
        if (error.equals("")) {
            //        ServicesLocator.AuthService.authenticate(this.user_name.getText(), this.user_password.getText());
        }
    }

    public void closeApplication (MouseEvent e) {
        System.exit(0);
    }

}
