package cu.edu.cujae.carRent.utils.visual;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.Field;

public class ScenesManager {
    public String users;
    public String tourists;

    public ScenesManager() {
        this.users = "../../visuals/pages/users/users.fxml";
        this.tourists = "../../visuals/pages/tourists/tourists.fxml";
    }

    public Object changeApContentTo(AnchorPane ap, String pageName, ScenesManager sm) throws IOException, NoSuchFieldException, IllegalAccessException {
        Field field = ScenesManager.class.getField(pageName);
        String location = (String) field.get(sm);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
        Node node = fxmlLoader.load();
        ap.getChildren().setAll(node);

        return fxmlLoader.getController();
    }
}
